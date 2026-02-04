package com.example.concert.user.presentation

import com.example.concert.user.application.UserService
import com.example.concert.user.application.dto.CreateUserRequest
import com.example.concert.user.application.dto.UpdateUserRequest
import com.example.concert.user.application.dto.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
	private val userService: UserService
) {

	@PostMapping
	fun createUser(
		@Valid @RequestBody request: CreateUserRequest
	): ResponseEntity<UserResponse> {
		val response = userService.createUser(request)
		return ResponseEntity.status(HttpStatus.CREATED).body(response)
	}

	@GetMapping("/{userId}")
	fun getUserById(
		@PathVariable userId: Long
	): ResponseEntity<UserResponse> {
		val response = userService.getUserById(userId)
		return ResponseEntity.ok(response)
	}

	@PutMapping("/{userId}")
	fun updateUser(
		@PathVariable userId: Long,
		@Valid @RequestBody request: UpdateUserRequest
	): ResponseEntity<Boolean> {
		val result = userService.updateUser(userId, request)
		return ResponseEntity.ok(result)
	}

	@DeleteMapping("/{userId}")
	fun deleteUser(
		@PathVariable userId: Long
	): ResponseEntity<Void> {
		userService.deleteUser(userId)
		return ResponseEntity.noContent().build()
	}
}
