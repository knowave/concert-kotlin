package com.example.concert.reservation.domain.repository

import com.example.concert.reservation.domain.entity.Reservation

interface ReservationRepository {
	fun save(reservation: Reservation): Reservation
	fun findById(id: Long): Reservation?
	fun findByUserId(userId: Long): List<Reservation>
	fun findBySeatId(seatId: Long): Reservation?
}
