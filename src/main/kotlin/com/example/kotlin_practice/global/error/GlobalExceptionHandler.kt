package com.example.kotlin_practice.global.error

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode
import lombok.extern.slf4j.Slf4j
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {
    //비즈니스 로직에서의 에러
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse?> {
        val errorCode: ErrorCode = e.errorCode
        val response: ErrorResponse = ErrorResponse.of(errorCode, errorCode.message)
        e.printStackTrace()
        return ResponseEntity<ErrorResponse?>(response, HttpStatus.valueOf(errorCode.statusCode))
    }

    // validation 에러
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse?> {
        val errorCode: ErrorCode = ErrorCode.BAD_REQUEST
        val response: ErrorResponse = ErrorResponse.of(errorCode, errorCode.message)
        e.printStackTrace()
        return ResponseEntity<ErrorResponse?>(response, HttpStatus.valueOf(errorCode.statusCode))
    }

    //그 외 에러들
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorCode: ErrorCode = ErrorCode.INTERNAL_SERVER_ERROR
        val response: ErrorResponse = ErrorResponse.of(errorCode, e.message!!)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
