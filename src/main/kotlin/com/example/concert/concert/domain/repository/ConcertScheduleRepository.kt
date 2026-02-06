package com.example.concert.concert.domain.repository

import com.example.concert.concert.domain.entity.ConcertSchedule

interface ConcertScheduleRepository {
	fun save(schedule: ConcertSchedule): ConcertSchedule
	fun findById(id: Long): ConcertSchedule?
	fun findByIdWithPessimisticLock(id: Long): ConcertSchedule?
	fun findByConcertId(concertId: Long): List<ConcertSchedule>
}
