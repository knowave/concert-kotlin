package com.example.concert.user.application

import com.example.concert.user.application.dto.CreateUserRequest
import com.example.concert.user.application.dto.UserResponse

interface UserService {
	fun createUser(request: CreateUserRequest): UserResponse
	fun getUserById(userId: Long): UserResponse
}
