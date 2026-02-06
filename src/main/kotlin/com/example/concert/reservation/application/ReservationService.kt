package com.example.concert.reservation.application

import com.example.concert.reservation.application.dto.CreateReservationRequest
import com.example.concert.reservation.application.dto.ReservationResponse

interface ReservationService {
	// 동시에 많은 요청이 들어와도 가장 먼저 요청한 사람만 예약 성공
	fun reserveSeat(request: CreateReservationRequest): ReservationResponse
	fun getReservation(reservationId: Long): ReservationResponse
	fun getReservationsByUserId(userId: Long): List<ReservationResponse>
	fun cancelReservation(reservationId: Long): ReservationResponse
}
