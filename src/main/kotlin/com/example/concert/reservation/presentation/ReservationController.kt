package com.example.concert.reservation.presentation

import com.example.concert.reservation.application.ReservationService
import com.example.concert.reservation.application.dto.CreateReservationRequest
import com.example.concert.reservation.application.dto.ReservationResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reservations")
class ReservationController(
	private val reservationService: ReservationService
) {

	@PostMapping
	fun reserveSeat(
		@Valid @RequestBody request: CreateReservationRequest
	): ResponseEntity<ReservationResponse> {
		val response = reservationService.reserveSeat(request)
		return ResponseEntity.status(HttpStatus.CREATED).body(response)
	}

	@GetMapping("/{reservationId}")
	fun getReservation(
		@PathVariable reservationId: Long
	): ResponseEntity<ReservationResponse> {
		val response = reservationService.getReservation(reservationId)
		return ResponseEntity.ok(response)
	}

	@GetMapping
	fun getReservationsByUserId(
		@RequestParam userId: Long
	): ResponseEntity<List<ReservationResponse>> {
		val response = reservationService.getReservationsByUserId(userId)
		return ResponseEntity.ok(response)
	}

	@DeleteMapping("/{reservationId}")
	fun cancelReservation(
		@PathVariable reservationId: Long
	): ResponseEntity<ReservationResponse> {
		val response = reservationService.cancelReservation(reservationId)
		return ResponseEntity.ok(response)
	}
}
