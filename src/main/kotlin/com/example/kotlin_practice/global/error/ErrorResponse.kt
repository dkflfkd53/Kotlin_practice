package com.example.kotlin_practice.global.error

import com.example.kotlin_practice.global.error.exception.ErrorCode
import java.time.LocalDateTime

class ErrorResponse(
    private val message: String? = null,
    private val status: Int? = null,
    private val timestamp: LocalDateTime? = null,
    private val description: String? = null
) {

    companion object {
        fun of(errorCode: ErrorCode, description: String): ErrorResponse {
            return ErrorResponse(
                message = errorCode.message,
                status = errorCode.statusCode,
                timestamp = LocalDateTime.now(),
                description = description
            )
        }

        fun of(statusCode: Int, description: String): ErrorResponse {
            return ErrorResponse(
                message = description,
                status = statusCode,
                timestamp = LocalDateTime.now(),
                description = description
            )
        }
    }
}


