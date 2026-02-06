package com.example.concert.reservation.application.dto

import com.example.concert.reservation.domain.entity.Reservation
import java.math.BigDecimal
import java.time.LocalDateTime

data class ReservationResponse(
	val id: Long,
	val userId: Long,
	val seatId: Long,
	val status: String,
	val price: BigDecimal,
	val confirmedAt: LocalDateTime?,
	val cancelledAt: LocalDateTime?,
	val createdAt: LocalDateTime?
) {
	companion object {
		fun from(reservation: Reservation): ReservationResponse {
			return ReservationResponse(
				id = reservation.id!!,
				userId = reservation.userId,
				seatId = reservation.seatId,
				status = reservation.status,
				price = reservation.price,
				confirmedAt = reservation.confirmedAt,
				cancelledAt = reservation.cancelledAt,
				createdAt = reservation.createdAt
			)
		}
	}
}
