package com.example.concert.user.domain.repository

import com.example.concert.user.domain.entity.User

interface UserRepository {
	fun save(user: User): User
	fun findByEmail(email: String): User?
	fun findByUsername(username: String): User?
	fun existsByEmail(email: String): Boolean
	fun existsByUsername(username: String): Boolean
}
