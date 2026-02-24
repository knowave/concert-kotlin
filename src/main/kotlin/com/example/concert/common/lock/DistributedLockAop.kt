package com.example.concert.common.lock

import com.example.concert.common.exception.LockAcquisitionFailedException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component

/**
 * @Order(1): @Transactional(기본값 Ordered.LOWEST_PRECEDENCE)보다 먼저 실행됨
 *
 * 실행 순서:
 *   락 획득 → 트랜잭션 시작 → 메서드 실행 → 트랜잭션 커밋 → 락 해제
 *
 * 이 순서가 중요한 이유:
 *   트랜잭션 커밋 전에 락을 해제하면 다른 스레드가 커밋 전 데이터를 읽을 수 있음
 */
@Aspect
@Component
@Order(1)
class DistributedLockAop(
    private val redissonClient: RedissonClient
) {

    private val log = LoggerFactory.getLogger(javaClass)
    private val expressionParser = SpelExpressionParser()

    @Around("@annotation(distributedLock)")
    fun lock(joinPoint: ProceedingJoinPoint, distributedLock: DistributedLock): Any? {
        val signature = joinPoint.signature as MethodSignature
        val lockKey = resolveLockKey(distributedLock.key, signature, joinPoint.args)
        val lock = redissonClient.getLock(lockKey)

        log.debug("분산 락 획득 시도: key={}, waitTime={}s, leaseTime={}s", lockKey, distributedLock.waitTime, distributedLock.leaseTime)

        val acquired = lock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, distributedLock.timeUnit)
        if (!acquired) {
            throw LockAcquisitionFailedException(lockKey)
        }

        log.debug("분산 락 획득 성공: key={}", lockKey)

        try {
            return joinPoint.proceed()
        } finally {
            if (lock.isHeldByCurrentThread) {
                lock.unlock()
                log.debug("분산 락 해제: key={}", lockKey)
            }
        }
    }

    private fun resolveLockKey(keyExpression: String, signature: MethodSignature, args: Array<Any?>): String {
        val context = StandardEvaluationContext()
        signature.parameterNames.forEachIndexed { index, name ->
            context.setVariable(name, args[index])
        }
        return expressionParser.parseExpression(keyExpression).getValue(context, String::class.java)
            ?: throw IllegalArgumentException("락 키를 생성할 수 없습니다: $keyExpression")
    }
}