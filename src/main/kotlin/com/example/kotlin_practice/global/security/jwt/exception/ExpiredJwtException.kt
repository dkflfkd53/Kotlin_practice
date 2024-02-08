package com.example.kotlin_practice.global.security.jwt.exception

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode

class ExpiredTokenException private constructor() : BusinessException(ErrorCode.EXPIRED_TOKEN) {
    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}
