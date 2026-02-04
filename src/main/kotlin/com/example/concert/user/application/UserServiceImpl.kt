package com.example.concert.user.application

import com.example.concert.common.exception.DuplicateEmailException
import com.example.concert.common.exception.DuplicateUsernameException
import com.example.concert.common.exception.UserNotFoundException
import com.example.concert.user.application.dto.CreateUserRequest
import com.example.concert.user.application.dto.UpdateUserRequest
import com.example.concert.user.application.dto.UserResponse
import com.example.concert.user.domain.entity.User
import com.example.concert.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl(
	private val userRepository: UserRepository
) : UserService {

	override fun createUser(request: CreateUserRequest): UserResponse {
		// 1. 이메일 중복 확인
		if (userRepository.existsByEmail(request.email)) {
			throw DuplicateEmailException(request.email)
		}

		// 2. 사용자명 중복 확인
		if (userRepository.existsByUsername(request.username)) {
			throw DuplicateUsernameException(request.username)
		}

		// 3. 사용자 생성
		val user = User(
			username = request.username,
			email = request.email
		)

		val savedUser = userRepository.save(user)

		return UserResponse.from(savedUser)
	}

	@Transactional(readOnly = true)
	override fun getUserById(userId: Long): UserResponse {
		val user = userRepository.findById(userId)
			?: throw UserNotFoundException(userId)
		return UserResponse.from(user)
	}

	override fun updateUser(userId: Long, request: UpdateUserRequest): Boolean {
		val user = userRepository.findById(userId)
			?: throw UserNotFoundException(userId)

		request.email?.let { newEmail ->
			if (newEmail != user.email && userRepository.existsByEmail(newEmail)) {
				throw DuplicateEmailException(newEmail)
			}
			user.email = newEmail
		}

		request.username?.let { newUsername ->
			if (newUsername != user.username && userRepository.existsByUsername(newUsername)) {
				throw DuplicateUsernameException(newUsername)
			}
			user.username = newUsername
		}

		userRepository.save(user)
		return true
	}

	override fun deleteUser(userId: Long) {
		val user = userRepository.findById(userId)
			?: throw UserNotFoundException(userId)
		userRepository.deleteById(user.id!!)
	}
}
