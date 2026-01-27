package com.example.concert.common.exception

import java.math.BigDecimal

open class BaseException(
	val errorCode: ErrorCode,
	override val message: String = errorCode.message,
	val data: Any? = null
) : RuntimeException(message)

class InvalidChargeAmountException(
	val amount: BigDecimal
) : BaseException(
	errorCode = ErrorCode.INVALID_CHARGE_AMOUNT,
	message = "충전 금액은 0보다 커야 합니다. 요청 금액: $amount",
	data = mapOf("amount" to amount)
)

class InsufficientBalanceException(
	val balance: BigDecimal,
	val requiredAmount: BigDecimal
) : BaseException(
	errorCode = ErrorCode.INSUFFICIENT_BALANCE,
	message = "잔액이 부족합니다. 현재 잔액: $balance, 필요 금액: $requiredAmount",
	data = mapOf(
		"balance" to balance,
		"requiredAmount" to requiredAmount
	)
)