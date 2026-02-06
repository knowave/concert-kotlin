package com.example.concert.reservation.infrastructure.persistence

import com.example.concert.reservation.domain.repository.ReservationRepository
import com.example.concert.reservation.domain.entity.Reservation
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(
	private val jpaReservationRepository: JpaReservationRepository
) : ReservationRepository {

	override fun save(reservation: Reservation): Reservation {
		return jpaReservationRepository.save(reservation)
	}

	override fun findById(id: Long): Reservation? {
		return jpaReservationRepository.findById(id).orElse(null)
	}

	override fun findByUserId(userId: Long): List<Reservation> {
		return jpaReservationRepository.findByUserId(userId)
	}

	override fun findBySeatId(seatId: Long): Reservation? {
		return jpaReservationRepository.findBySeatId(seatId)
	}
}
