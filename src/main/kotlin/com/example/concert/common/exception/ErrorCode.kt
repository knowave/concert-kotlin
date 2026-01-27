package com.example.concert.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode (
	val code: String,
	val message: String,
	val status: HttpStatus
) {
	// User
	INVALID_CHARGE_AMOUNT("U001", "충전 금액은 0보다 커야 합니다", HttpStatus.BAD_REQUEST),
	INSUFFICIENT_BALANCE("U002", "잔액이 부족합니다", HttpStatus.BAD_REQUEST),

	// Concert
	NO_AVAILABLE_SEATS("C001", "예약 가능한 좌석이 없습니다", HttpStatus.NOT_FOUND),
	SEAT_RESTORE_ERROR("C002", "좌석 수 복구 오류", HttpStatus.INTERNAL_SERVER_ERROR),

	// Seat
	ALREADY_RESERVED_SEAT("S001", "이미 예약된 좌석입니다", HttpStatus.CONFLICT),
	NOT_TEMPORARY_RESERVED("S002", "임시 예약 상태가 아닙니다", HttpStatus.BAD_REQUEST)
}