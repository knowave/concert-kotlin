package com.example.concert.concert.domain.entity

import com.example.concert.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
	name = "concerts",
	indexes = [
		Index(name = "idx_title", columnList = "title"),
		Index(name = "idx_artist", columnList = "artist")
	]
)
data class Concert(
	@Column(nullable = false, length = 255)
	val title: String,

	@Column(columnDefinition = "TEXT")
	val description: String? = null,

	@Column(nullable = false, length = 255)
	val artist: String,
) : BaseEntity()