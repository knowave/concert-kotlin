package com.example.concert.user.infrastructure.persistence

import com.example.concert.user.domain.entity.User
import com.example.concert.user.domain.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
	private val jpaUserRepository: JpaUserRepository
) : UserRepository {

	override fun save(user: User): User {
		return jpaUserRepository.save(user)
	}

	override fun findByEmail(email: String): User? {
		return jpaUserRepository.findByEmail(email)
	}

	override fun findByUsername(username: String): User? {
		return jpaUserRepository.findByUsername(username)
	}

	override fun existsByEmail(email: String): Boolean {
		return jpaUserRepository.existsByEmail(email)
	}

	override fun existsByUsername(username: String): Boolean {
		return jpaUserRepository.existsByUsername(username)
	}
}
