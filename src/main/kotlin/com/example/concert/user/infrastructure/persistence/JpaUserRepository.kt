package com.example.concert.user.infrastructure.persistence

import com.example.concert.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<User, Long> {
	fun findByEmail(email: String): User?
	fun findByUsername(username: String): User?
	fun existsByEmail(email: String): Boolean
	fun existsByUsername(username: String): Boolean
}
