package com.example.concert.user.application.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest(
	@field:NotBlank(message = "사용자명은 필수입니다")
	@field:Size(min = 2, max = 100, message = "사용자명은 2자 이상 100자 이하여야 합니다")
	val username: String,

	@field:NotBlank(message = "이메일은 필수입니다")
	@field:Email(message = "올바른 이메일 형식이 아닙니다")
	@field:Size(max = 255, message = "이메일은 255자 이하여야 합니다")
	val email: String
)
