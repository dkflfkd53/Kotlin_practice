package com.example.kotlin_practice.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    public override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = jwtTokenProvider.resolveToken(request)
        if (token != null) {
            // Jwt에 담긴 정보를 통해 어떤 유저가 로그인 되었는지 식별하고,
            // Spring Security SecurityContextHolder를 통해 현재 요청에 유저의 정보를 등록한다.
            val authentication: Authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}