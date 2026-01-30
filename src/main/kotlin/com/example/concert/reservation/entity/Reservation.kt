package com.example.concert.reservation.entity

import com.example.concert.common.entity.BaseEntity
import com.example.concert.common.exception.AlreadyCancelledReservationException
import com.example.concert.common.exception.NotPendingReservationException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(
	name = "reservations",
	indexes = [
		Index(name = "idx_user_id", columnList = "user_id"),
		Index(name = "idx_seat_id", columnList = "seat_id"),
		Index(name = "idx_status", columnList = "status"),
		Index(name = "idx_created_at", columnList = "created_at")
	]
)
data class Reservation(
	@Column(name = "user_id", nullable = false)
	val userId: Long,

	@Column(name = "seat_id", nullable = false)
	val seatId: Long,

	@Column(nullable = false, length = 20)
	var status: String = ReservationStatus.PENDING.name,

	@Column(nullable = false, precision = 10, scale = 2)
	val price: BigDecimal,

	@Column(name = "confirmed_at")
	var confirmedAt: LocalDateTime? = null,

	@Column(name = "cancelled_at")
	var cancelledAt: LocalDateTime? = null,
) : BaseEntity() {

	// 결제 완료 후 예약 확정 처리
	fun confirm() {
		require(status == ReservationStatus.PENDING.name) {
			throw NotPendingReservationException(status)
		}

		status = ReservationStatus.CONFIRMED.name
		confirmedAt = LocalDateTime.now()
	}

	// 예약 취소
	fun cancel() {
		require(status != ReservationStatus.CANCELLED.name) {
			throw AlreadyCancelledReservationException(reservationId = id)
		}

		status = ReservationStatus.CANCELLED.name
		cancelledAt = LocalDateTime.now()
	}
}

enum class ReservationStatus {
	PENDING,
	CONFIRMED,
	CANCELLED
}
