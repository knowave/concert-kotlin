package com.example.concert.concert.application

import com.example.concert.common.exception.ScheduleNotFoundException
import com.example.concert.concert.domain.repository.ConcertScheduleRepository
import com.example.concert.concert.domain.entity.ConcertSchedule
import org.springframework.stereotype.Service

@Service
class ConcertScheduleServiceImpl(
	private val concertScheduleRepository: ConcertScheduleRepository
) : ConcertScheduleService {

	override fun getScheduleWithLock(scheduleId: Long): ConcertSchedule {
		return concertScheduleRepository.findByIdWithPessimisticLock(scheduleId)
			?: throw ScheduleNotFoundException(scheduleId)
	}

	override fun getSchedule(scheduleId: Long): ConcertSchedule {
		return concertScheduleRepository.findById(scheduleId)
			?: throw ScheduleNotFoundException(scheduleId)
	}

	override fun decreaseAvailableSeats(scheduleId: Long): ConcertSchedule {
		val schedule = concertScheduleRepository.findById(scheduleId)
			?: throw ScheduleNotFoundException(scheduleId)

		schedule.decreaseAvailableSeats()
		return concertScheduleRepository.save(schedule)
	}

	override fun increaseAvailableSeats(scheduleId: Long): ConcertSchedule {
		val schedule = concertScheduleRepository.findById(scheduleId)
			?: throw ScheduleNotFoundException(scheduleId)

		schedule.increaseAvailableSeats()
		return concertScheduleRepository.save(schedule)
	}

	override fun getSchedulesByConcertId(concertId: Long): List<ConcertSchedule> {
		return concertScheduleRepository.findByConcertId(concertId)
	}
}
