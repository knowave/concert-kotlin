package com.example.concert.user.domain.entity

import com.example.concert.common.entity.BaseEntity
import com.example.concert.common.exception.InsufficientBalanceException
import com.example.concert.common.exception.InvalidChargeAmountException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(
	name = "users",
	indexes = [
		Index(name = "idx_email", columnList = "email")
	]
)
data class User (
	@Column(nullable = false, length = 100, unique = true)
	var username: String,

	@Column(nullable = false, length = 255, unique = true)
	var email: String,

	@Column(nullable = false, precision = 15, scale = 2)
	var balance: BigDecimal = BigDecimal.ZERO
) : BaseEntity() {

	fun charge(amount: BigDecimal) {
		require(amount > BigDecimal.ZERO) { throw InvalidChargeAmountException(amount) }

		balance = balance.add(amount)
	}

	fun use(amount: BigDecimal) {
		require(balance >= amount) { throw InsufficientBalanceException(balance, amount) }

		balance = balance.subtract(amount)
	}
}
