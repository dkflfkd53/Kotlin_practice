package com.example.kotlin_practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinPracticeApplication

fun main(args: Array<String>) {
    runApplication<KotlinPracticeApplication>(*args)
}
