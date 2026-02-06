package com.example.concert.concert.domain.repository

import com.example.concert.concert.domain.entity.Seat

interface SeatRepository {
	fun save(seat: Seat): Seat
	fun findById(id: Long): Seat?
	fun findByIdWithPessimisticLock(id: Long): Seat?
	fun findByScheduleId(scheduleId: Long): List<Seat>
	fun findAvailableSeatsByScheduleId(scheduleId: Long): List<Seat>
}
