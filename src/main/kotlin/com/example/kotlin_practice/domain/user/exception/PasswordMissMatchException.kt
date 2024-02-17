package com.example.kotlin_practice.domain.user.exception

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode

class PasswordMissMatchException private constructor() : BusinessException(ErrorCode.PASSWORD_MISS_MATCH) {
    companion object {
        @JvmField
        val EXCEPTION = PasswordMissMatchException()
    }
}
