package com.example.concert.concert.infrastructure.persistence

import com.example.concert.concert.domain.entity.Seat
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface JpaSeatRepository : JpaRepository<Seat, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT s FROM Seat s WHERE s.id = :id")
	fun findByIdWithPessimisticLock(id: Long): Seat?

	fun findByScheduleId(scheduleId: Long): List<Seat>

	@Query("SELECT s FROM Seat s WHERE s.scheduleId = :scheduleId AND s.status = 'AVAILABLE'")
	fun findAvailableSeatsByScheduleId(scheduleId: Long): List<Seat>
}
