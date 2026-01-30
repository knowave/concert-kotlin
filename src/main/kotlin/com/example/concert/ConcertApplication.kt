package com.example.concert

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import io.github.cdimascio.dotenv.dotenv

@SpringBootApplication
@EnableJpaAuditing
class ConcertApplication

fun main(args: Array<String>) {
	val env = System.getProperty("spring.profiles.active") ?: System.getenv("SPRING_PROFILES_ACTIVE") ?: "default"

	if (env !== "prod") {
		val dotenv = dotenv()

		dotenv.entries().forEach { entry ->
			System.setProperty(entry.key, entry.value)
		}
	}
	runApplication<ConcertApplication>(*args)
}
