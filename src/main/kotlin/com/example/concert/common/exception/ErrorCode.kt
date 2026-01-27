package com.example.concert.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode (
	val code: String,
	val message: String,
	val status: HttpStatus
)