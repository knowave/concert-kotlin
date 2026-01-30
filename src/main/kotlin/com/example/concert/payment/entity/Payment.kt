package com.example.concert.domain.payment.entity

import com.example.concert.common.entity.BaseEntity
import com.example.concert.common.exception.NotPendingPaymentException
import jakarta.persistence.Column
import java.math.BigDecimal
import java.time.LocalDateTime

data class Payment(
	@Column(name = "user_id", nullable = false)
	val userId: Long,

	@Column(name = "reservation_id", nullable = false)
	val reservationId: Long,

	@Column(nullable = false, precision = 10, scale = 2)
	val amount: BigDecimal,

	@Column(nullable = false, length = 20)
	var status: String = PaymentStatus.PENDING.name,

	@Column(name = "completed_at")
	var completedAt: LocalDateTime? = null,
) : BaseEntity() {

	// 결제 완료
	fun complete() {
		require(status == PaymentStatus.PENDING.name) {
			throw NotPendingPaymentException(status)
		}

		status = PaymentStatus.COMPLETED.name
		completedAt = LocalDateTime.now()
	}

	// 결제 실패
	fun fail() {
		require(status == PaymentStatus.PENDING.name) {
			throw NotPendingPaymentException(status)
		}

		status = PaymentStatus.FAILED.name
	}
}

enum class PaymentStatus {
	PENDING,
	COMPLETED,
	FAILED
}