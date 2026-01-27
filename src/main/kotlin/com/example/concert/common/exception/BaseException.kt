package com.example.concert.common.exception

open class BaseException(
	val errorCode: ErrorCode,
	override val message: String = errorCode.message,
	val data: Any? = null
) : RuntimeException(message)