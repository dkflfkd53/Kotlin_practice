package com.example.kotlin_practice.global.error

import com.example.kotlin_practice.global.error.exception.BusinessException
import com.example.kotlin_practice.global.error.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    @Throws(IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            val errorCode: ErrorCode = e.errorCode
            writeErrorResponse(
                response,
                errorCode.statusCode,
                ErrorResponse.of(errorCode, errorCode.message)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            writeErrorResponse(response, response.status, ErrorResponse.of(response.status, e.message!!))
        }
    }

    @Throws(IOException::class)
    private fun writeErrorResponse(
        response: HttpServletResponse,
        status: Int,
        errorResponse:
        ErrorResponse
    ) {
        response.status = status
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        objectMapper.writeValue(response.writer, errorResponse)
    }
}
