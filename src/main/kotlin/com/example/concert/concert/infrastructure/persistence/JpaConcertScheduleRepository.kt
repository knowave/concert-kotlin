package com.example.concert.concert.infrastructure.persistence

import com.example.concert.concert.domain.entity.ConcertSchedule
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface JpaConcertScheduleRepository : JpaRepository<ConcertSchedule, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT cs FROM ConcertSchedule cs WHERE cs.id = :id")
	fun findByIdWithPessimisticLock(id: Long): ConcertSchedule?

	fun findByConcertId(concertId: Long): List<ConcertSchedule>
}
