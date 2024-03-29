package com.example.kotlin_practice.domain.post.exception

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode

object PostNotFoundException : BusinessException(
    ErrorCode.POST_NOT_FOUND
)