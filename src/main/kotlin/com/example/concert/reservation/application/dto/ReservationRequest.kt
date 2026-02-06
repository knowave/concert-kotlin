package com.example.concert.reservation.application.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreateReservationRequest(
	@field:NotNull(message = "사용자 ID는 필수입니다")
	@field:Positive(message = "사용자 ID는 양수여야 합니다")
	val userId: Long,

	@field:NotNull(message = "좌석 ID는 필수입니다")
	@field:Positive(message = "좌석 ID는 양수여야 합니다")
	val seatId: Long
)
