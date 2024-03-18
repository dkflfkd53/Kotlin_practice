package com.example.kotlin_practice.global.config

import com.example.kotlin_practice.global.error.GlobalExceptionFilter
import com.example.kotlin_practice.global.security.jwt.JwtFilter
import com.example.kotlin_practice.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {
        val jwtTokenFilter = JwtFilter(jwtTokenProvider)
        val globalExceptionFilter = GlobalExceptionFilter(objectMapper)
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(globalExceptionFilter, JwtFilter::class.java)
    }
}
