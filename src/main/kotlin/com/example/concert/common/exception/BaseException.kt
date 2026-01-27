package com.example.concert.common.exception

import java.math.BigDecimal

open class BaseException(
	val errorCode: ErrorCode,
	override val message: String = errorCode.message,
	val data: Any? = null
) : RuntimeException(message)

// User
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

// Concert
class NoAvailableSeatsException(
	val scheduleId: Long? = null
) : BaseException(
	errorCode = ErrorCode.NO_AVAILABLE_SEATS,
	message = "예약 가능한 좌석이 없습니다",
	data = scheduleId?.let { mapOf("scheduleId" to it) }
)

class SeatRestoreException(
	val availableSeats: Int,
	val totalSeats: Int
) : BaseException(
	errorCode = ErrorCode.SEAT_RESTORE_ERROR,
	message = "좌석 수 복구 오류. 현재 좌석: $availableSeats, 총 좌석: $totalSeats",
	data = mapOf(
		"availableSeats" to availableSeats,
		"totalSeats" to totalSeats
	)
)

class AlreadyReservedSeatException(
	val seatId: Long? = null,
	val status: String
) : BaseException(
	errorCode = ErrorCode.ALREADY_RESERVED_SEAT,
	message = "이미 예약된 좌석입니다. 현재 상태: $status",
	data = mapOf(
		"seatId" to seatId,
		"status" to status
	)
)

class NotTemporaryReservedException(
	val seatId: Long? = null,
	val status: String
) : BaseException(
	errorCode = ErrorCode.NOT_TEMPORARY_RESERVED,
	message = "임시 예약 상태가 아닙니다. 현재 상태: $status",
	data = mapOf(
		"seatId" to seatId,
		"status" to status
	)
)