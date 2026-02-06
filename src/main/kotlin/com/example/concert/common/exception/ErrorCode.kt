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
	DUPLICATE_EMAIL("U003", "이미 존재하는 이메일입니다", HttpStatus.CONFLICT),
	DUPLICATE_USERNAME("U004", "이미 존재하는 사용자명입니다", HttpStatus.CONFLICT),
	USER_NOT_FOUND("U005", "사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),

	// Concert
	NO_AVAILABLE_SEATS("C001", "예약 가능한 좌석이 없습니다", HttpStatus.NOT_FOUND),
	SEAT_RESTORE_ERROR("C002", "좌석 수 복구 오류", HttpStatus.INTERNAL_SERVER_ERROR),

	// Seat
	ALREADY_RESERVED_SEAT("S001", "이미 예약된 좌석입니다", HttpStatus.CONFLICT),
	NOT_TEMPORARY_RESERVED("S002", "임시 예약 상태가 아닙니다", HttpStatus.BAD_REQUEST),

	// Reservation
	NOT_PENDING_RESERVATION("R001", "현재 대기 중인 예약이 아닙니다", HttpStatus.BAD_REQUEST),
	ALREADY_CANCELLED_RESERVATION("R002", "이미 취소된 예약입니다", HttpStatus.BAD_REQUEST),

	// Payment
	NOT_PENDING_PAYMENT("P001", "결제 대기 상태가 아닙니다", HttpStatus.BAD_REQUEST),

	// Seat
	SEAT_NOT_FOUND("S003", "좌석을 찾을 수 없습니다", HttpStatus.NOT_FOUND),

	// Schedule
	SCHEDULE_NOT_FOUND("CS001", "콘서트 일정을 찾을 수 없습니다", HttpStatus.NOT_FOUND),

	// Reservation
	RESERVATION_NOT_FOUND("R003", "예약을 찾을 수 없습니다", HttpStatus.NOT_FOUND)
}