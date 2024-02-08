package com.example.kotlin_practice.global.error.exception

open class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException()
