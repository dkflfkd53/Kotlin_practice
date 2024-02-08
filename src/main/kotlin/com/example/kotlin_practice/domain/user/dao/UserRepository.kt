package com.example.kotlin_practice.domain.user.dao

import com.example.kotlin_practice.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUserName(userName: String): User
}