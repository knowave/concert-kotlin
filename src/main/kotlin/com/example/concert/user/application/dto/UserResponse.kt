package com.example.concert.user.application.dto

import com.example.concert.user.domain.entity.User
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserResponse(
	val id: Long,
	val username: String,
	val email: String,
	val balance: BigDecimal,
	val createdAt: LocalDateTime?,
	val updatedAt: LocalDateTime?
) {
	companion object {
		fun from(user: User): UserResponse {
			return UserResponse(
				id = user.id!!,
				username = user.username,
				email = user.email,
				balance = user.balance,
				createdAt = user.createdAt,
				updatedAt = user.updatedAt
			)
		}
	}
}
