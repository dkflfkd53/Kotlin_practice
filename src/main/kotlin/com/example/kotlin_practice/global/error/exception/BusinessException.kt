package com.example.kotlin_practice.global.error.exception

abstract class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException()
