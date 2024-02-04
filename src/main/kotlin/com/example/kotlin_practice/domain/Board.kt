package com.example.kotlin_practice.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Board (
    @Id val id : UUID?,
    var title : String,
    var content : String
) {
}