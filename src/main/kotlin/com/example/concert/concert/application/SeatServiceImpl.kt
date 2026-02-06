package com.example.concert.concert.application

import com.example.concert.common.exception.SeatNotFoundException
import com.example.concert.concert.domain.repository.SeatRepository
import com.example.concert.concert.domain.entity.Seat
import com.example.concert.concert.domain.entity.SeatStatus
import org.springframework.stereotype.Service

@Service
class SeatServiceImpl(
	private val seatRepository: SeatRepository
) : SeatService {

	override fun getSeatWithLock(seatId: Long): Seat {
		return seatRepository.findByIdWithPessimisticLock(seatId)
			?: throw SeatNotFoundException(seatId)
	}

	override fun getSeat(seatId: Long): Seat {
		return seatRepository.findById(seatId)
			?: throw SeatNotFoundException(seatId)
	}

	override fun reserveSeat(seatId: Long, userId: Long): Seat {
		val seat = seatRepository.findByIdWithPessimisticLock(seatId)
			?: throw SeatNotFoundException(seatId)

		seat.reserve(userId)
		return seatRepository.save(seat)
	}

	override fun releaseSeat(seatId: Long): Seat {
		val seat = seatRepository.findByIdWithPessimisticLock(seatId)
			?: throw SeatNotFoundException(seatId)

		seat.status = SeatStatus.AVAILABLE.name
		seat.reservedBy = null
		seat.reservedAt = null
		seat.expiresAt = null

		return seatRepository.save(seat)
	}

	override fun save(seat: Seat): Seat {
		return seatRepository.save(seat)
	}

	override fun getAvailableSeatsByScheduleId(scheduleId: Long): List<Seat> {
		return seatRepository.findAvailableSeatsByScheduleId(scheduleId)
	}
}
