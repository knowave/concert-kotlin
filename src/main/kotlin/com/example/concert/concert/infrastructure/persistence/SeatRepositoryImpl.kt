package com.example.concert.concert.infrastructure.persistence

import com.example.concert.concert.domain.repository.SeatRepository
import com.example.concert.concert.domain.entity.Seat
import org.springframework.stereotype.Repository

@Repository
class SeatRepositoryImpl(
	private val jpaSeatRepository: JpaSeatRepository
) : SeatRepository {

	override fun save(seat: Seat): Seat {
		return jpaSeatRepository.save(seat)
	}

	override fun findById(id: Long): Seat? {
		return jpaSeatRepository.findById(id).orElse(null)
	}

	override fun findByIdWithPessimisticLock(id: Long): Seat? {
		return jpaSeatRepository.findByIdWithPessimisticLock(id)
	}

	override fun findByScheduleId(scheduleId: Long): List<Seat> {
		return jpaSeatRepository.findByScheduleId(scheduleId)
	}

	override fun findAvailableSeatsByScheduleId(scheduleId: Long): List<Seat> {
		return jpaSeatRepository.findAvailableSeatsByScheduleId(scheduleId)
	}
}
