package com.example.concert.concert.application

import com.example.concert.concert.domain.entity.Seat

interface SeatService {
	// 동시성 제어가 필요한 경우 사용
	fun getSeatWithLock(seatId: Long): Seat
	fun getSeat(seatId: Long): Seat
	// 동시성 제어가 필요한 경우 사용
	fun reserveSeat(seatId: Long, userId: Long): Seat
	// 동시성 제어가 필요한 경우 사용
	fun releaseSeat(seatId: Long): Seat
	fun save(seat: Seat): Seat
	// 스케줄별 예약 가능한 좌석 목록 조회
	fun getAvailableSeatsByScheduleId(scheduleId: Long): List<Seat>
}
