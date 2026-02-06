package com.example.concert.reservation.infrastructure.persistence

import com.example.concert.reservation.domain.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface JpaReservationRepository : JpaRepository<Reservation, Long> {
	fun findByUserId(userId: Long): List<Reservation>
	fun findBySeatId(seatId: Long): Reservation?
}
