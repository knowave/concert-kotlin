package com.example.concert.common.lock

import java.util.concurrent.TimeUnit

/**
 * Redis 분산 락 어노테이션
 *
 * key: SpEL 표현식으로 락 키 지정 (예: "'seat:lock:' + #request.seatId")
 * waitTime: 락 획득 대기 시간 (기본 5초)
 * leaseTime: 락 보유 시간 - 이 시간이 지나면 자동 해제 (기본 10초)
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DistributedLock(
    val key: String,
    val waitTime: Long = 5L,
    val leaseTime: Long = 10L,
    val timeUnit: TimeUnit = TimeUnit.SECONDS
)