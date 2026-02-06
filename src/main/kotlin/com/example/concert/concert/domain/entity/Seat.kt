package com.example.concert.concert.domain.entity

import com.example.concert.common.entity.BaseEntity
import com.example.concert.common.exception.AlreadyReservedSeatException
import com.example.concert.common.exception.NotTemporaryReservedException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(
	name = "seats",
	uniqueConstraints = [
		UniqueConstraint(
			name = "uk_schedule_seat",
			columnNames = ["schedule_id", "seat_number"]
		)
	],
	indexes = [
		Index(name = "idx_schedule_status", columnList = "schedule_id, status"),
		Index(name = "idx_expires_at", columnList = "expires_at"),
		Index(name = "idx_reserved_by", columnList = "reserved_by")
	]
)
data class Seat(
	@Column(name = "schedule_id", nullable = false)
	val scheduleId: Long,

	@Column(nullable = false, length = 20)
	val seatNumber: String,

	@Column(nullable = false, precision = 10, scale = 2)
	val price: BigDecimal,

	@Column(nullable = false, length = 20)
	var status: String = SeatStatus.AVAILABLE.name,

	@Column(name = "reserved_by")
	var reservedBy: Long? = null,

	@Column(name = "reserved_at")
	var reservedAt: LocalDateTime? = null,

	@Column(name = "expires_at")
	var expiresAt: LocalDateTime? = null,
) : BaseEntity() {

	fun reserve(userId: Long) {
		require(status == SeatStatus.AVAILABLE.name) {
			throw AlreadyReservedSeatException(seatId = id, status = status)
		}

		status = SeatStatus.TEMP_RESERVED.name
		reservedBy = userId
		reservedAt = LocalDateTime.now()
		expiresAt = LocalDateTime.now().plusMinutes(5)
	}

	fun confirm() {
		require(status == SeatStatus.TEMP_RESERVED.name) {
			throw NotTemporaryReservedException(seatId = id, status = status)
		}

		status = SeatStatus.CONFIRMED.name
		expiresAt = null
	}
}

enum class SeatStatus {
	AVAILABLE,      // 예약 가능
	TEMP_RESERVED,  // 임시 점유 (5분)
	CONFIRMED       // 예약 확정
}