package com.example.concert.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode (
	val code: String,
	val message: String,
	val status: HttpStatus
) {
	INVALID_CHARGE_AMOUNT("U001", "충전 금액은 0보다 커야 합니다", HttpStatus.BAD_REQUEST),
	INSUFFICIENT_BALANCE("U002", "잔액이 부족합니다", HttpStatus.BAD_REQUEST)
}