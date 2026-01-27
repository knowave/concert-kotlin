package com.example.concert.domain.concert.entity

import com.example.concert.common.entity.BaseEntity
import com.example.concert.common.exception.NoAvailableSeatsException
import com.example.concert.common.exception.SeatRestoreException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
	name = "concert_schedules",
	indexes = [
		Index(name = "idx_concert_date", columnList = "concert_id, performance_date"),
		Index(name = "idx_performance_date", columnList = "performance_date"),
		Index(name = "idx_available_seats", columnList = "available_seats")
	]
)
data class ConcertSchedule(
	@Column(name = "concert_id", nullable = false)
	val concertId: Long,

	@Column(nullable = false)
	val performanceDate: LocalDateTime,

	@Column(nullable = false)
	val totalSeats: Int,

	@Column(nullable = false)
	var availableSeats: Int,
) : BaseEntity() {
	fun decreaseAvailableSeats() {
		require(availableSeats > 0) { NoAvailableSeatsException() }
		availableSeats--
		updatedAt = LocalDateTime.now()
	}

	fun increaseAvailableSeats() {
		require(availableSeats < totalSeats) { SeatRestoreException(availableSeats, totalSeats) }
		availableSeats++
		updatedAt = LocalDateTime.now()
	}

	fun isSoldOut(): Boolean {
		return availableSeats <= 0
	}
}
