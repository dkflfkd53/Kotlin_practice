package com.example.kotlin_practice.global.security.auth

import com.example.kotlin_practice.domain.user.dao.UserRepository
import com.example.kotlin_practice.domain.user.domain.User
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(userName: String): UserDetails {
            val user: User = userRepository.findByUserName(userName)

            return CustomUserDetails(user)
    }
}
