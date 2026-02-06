package com.example.concert.concert.application

import com.example.concert.concert.domain.entity.ConcertSchedule

interface ConcertScheduleService {
	// 동시성 제어가 필요한 경우 사용
	fun getScheduleWithLock(scheduleId: Long): ConcertSchedule
	fun getSchedule(scheduleId: Long): ConcertSchedule
	// 잔여 좌석 수 감소 (비관적 락 적용)
	fun decreaseAvailableSeats(scheduleId: Long): ConcertSchedule
	// 잔여 좌석 수 증가 (비관적 락 적용)
	fun increaseAvailableSeats(scheduleId: Long): ConcertSchedule
	fun getSchedulesByConcertId(concertId: Long): List<ConcertSchedule>
}
