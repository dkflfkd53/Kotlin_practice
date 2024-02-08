package com.example.kotlin_practice.global.security.jwt

import com.example.kotlin_practice.domain.user.dao.UserRepository
import com.example.kotlin_practice.domain.user.domain.User
import com.example.kotlin_practice.global.security.auth.CustomUserDetailsService
import com.example.kotlin_practice.global.security.jwt.dto.response.TokenResponse
import com.example.kotlin_practice.global.security.jwt.exception.ExpiredTokenException
import com.example.kotlin_practice.global.security.jwt.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository,
    private val customUserDetailsService: CustomUserDetailsService
) {

    // access token 생성
    fun createAccessToken(classNumber: String?): String {
        val now = Date()
        return Jwts.builder()
            .setSubject(classNumber)
            .claim("type", "access")
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.accessExpiration * 1000))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .compact()
    }

    // 토큰에 담겨있는 userId로 SpringSecurity Authentication 정보를 반환하는 메서드
    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)
        val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(claims.subject)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts
                .parser()
                .setSigningKey(jwtProperties.secret)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException.EXCEPTION
        } catch (e: Exception) {
            throw InvalidTokenException.EXCEPTION
        }
    }

    fun receiveToken(userName: String?): TokenResponse {
        val user: User = userRepository.findByUserName(userName!!)
        return TokenResponse(
            accessToken = createAccessToken(userName)
        )
    }

    // HTTP 요청 헤더에서 토큰을 가져오는 메서드
    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(jwtProperties.header)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.prefix) && bearerToken.length > jwtProperties.prefix
                .length + 1
        ) {
            bearerToken.substring(7)
        } else null
    }
}
