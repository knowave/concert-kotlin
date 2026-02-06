package com.example.concert.concert.infrastructure.persistence

import com.example.concert.concert.domain.repository.ConcertScheduleRepository
import com.example.concert.concert.domain.entity.ConcertSchedule
import org.springframework.stereotype.Repository

@Repository
class ConcertScheduleRepositoryImpl(
	private val jpaConcertScheduleRepository: JpaConcertScheduleRepository
) : ConcertScheduleRepository {

	override fun save(schedule: ConcertSchedule): ConcertSchedule {
		return jpaConcertScheduleRepository.save(schedule)
	}

	override fun findById(id: Long): ConcertSchedule? {
		return jpaConcertScheduleRepository.findById(id).orElse(null)
	}

	override fun findByIdWithPessimisticLock(id: Long): ConcertSchedule? {
		return jpaConcertScheduleRepository.findByIdWithPessimisticLock(id)
	}

	override fun findByConcertId(concertId: Long): List<ConcertSchedule> {
		return jpaConcertScheduleRepository.findByConcertId(concertId)
	}
}
