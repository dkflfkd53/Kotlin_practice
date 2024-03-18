package com.example.kotlin_practice.global.config

import com.example.kotlin_practice.global.security.jwt.JwtFilter
import com.example.kotlin_practice.global.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig(
    //private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider
) {

    @Bean
    @Throws(Exception::class)
    protected fun configure(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .cors { cors: CorsConfigurer<HttpSecurity?> ->
                cors
                    .configurationSource(corsConfigurationSource())
            }
            .headers { header -> header.frameOptions { framOptions -> framOptions.disable() }}
            .sessionManagement { sessionManagement: SessionManagementConfigurer<HttpSecurity?> ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { auth -> auth
                .requestMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
            }

            .addFilterBefore(JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)

        return httpSecurity.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*") // 모든 도메인 허용
        configuration.setAllowedMethods(
            mutableListOf(
                "OPTIONS",
                "GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE"
            )
        ) // HTTP 메서드 허용
        configuration.allowCredentials = false
        configuration.addAllowedHeader("*") // 모든 헤더 허용
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration) // 모든 경로에 대해 위에서 설정한 CORS 설정 적용
        return source
    }
}