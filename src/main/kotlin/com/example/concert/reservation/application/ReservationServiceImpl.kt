package com.example.concert.reservation.application

import com.example.concert.common.exception.ReservationNotFoundException
import com.example.concert.common.lock.DistributedLock
import com.example.concert.concert.application.ConcertScheduleService
import com.example.concert.concert.application.SeatService
import com.example.concert.reservation.application.dto.CreateReservationRequest
import com.example.concert.reservation.application.dto.ReservationResponse
import com.example.concert.reservation.domain.repository.ReservationRepository
import com.example.concert.reservation.domain.entity.Reservation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationServiceImpl(
	private val reservationRepository: ReservationRepository,
	private val seatService: SeatService,
	private val concertScheduleService: ConcertScheduleService
) : ReservationService {

	@DistributedLock(key = "'seat:lock:' + #request.seatId")
	@Transactional
	override fun reserveSeat(request: CreateReservationRequest): ReservationResponse {
		val seat = seatService.reserveSeat(request.seatId, request.userId)
		concertScheduleService.decreaseAvailableSeats(seat.scheduleId)

		val reservation = Reservation(
			userId = request.userId,
			seatId = seat.id!!,
			price = seat.price
		)

		val savedReservation = reservationRepository.save(reservation)

		return ReservationResponse.from(savedReservation)
	}

	@Transactional(readOnly = true)
	override fun getReservation(reservationId: Long): ReservationResponse {
		val reservation = reservationRepository.findById(reservationId)
			?: throw ReservationNotFoundException(reservationId)

		return ReservationResponse.from(reservation)
	}

	@Transactional(readOnly = true)
	override fun getReservationsByUserId(userId: Long): List<ReservationResponse> {
		return reservationRepository.findByUserId(userId)
			.map { ReservationResponse.from(it) }
	}

	@DistributedLock(key = "'reservation:lock:' + #reservationId")
	@Transactional
	override fun cancelReservation(reservationId: Long): ReservationResponse {
		val reservation = reservationRepository.findById(reservationId)
			?: throw ReservationNotFoundException(reservationId)

		val seat = seatService.getSeat(reservation.seatId)

		seatService.releaseSeat(reservation.seatId)
		concertScheduleService.increaseAvailableSeats(seat.scheduleId)

		reservation.cancel()
		reservationRepository.save(reservation)

		return ReservationResponse.from(reservation)
	}
}
