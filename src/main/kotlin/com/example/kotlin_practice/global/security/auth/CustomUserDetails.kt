package com.example.kotlin_practice.global.security.auth

import com.example.kotlin_practice.domain.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(user: User) : UserDetails {
    override fun getUsername(): String {
        return user.userName
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return ArrayList(setOf(SimpleGrantedAuthority("ROLE_" + user.userName.toString())))
    }

    override fun getPassword(): String {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    val user: User

    init {
        this.user = user
    }
}