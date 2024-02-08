package com.example.kotlin_practice.global.security.jwt.exception

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode

class InvalidTokenException private constructor() : BusinessException(ErrorCode.INVALID_TOKEN) {
    companion object {
        @JvmField
        val EXCEPTION = InvalidTokenException()
    }
}