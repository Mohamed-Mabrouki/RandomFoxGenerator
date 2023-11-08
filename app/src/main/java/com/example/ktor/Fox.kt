package com.example.ktor

import kotlinx.serialization.Serializable

@Serializable
data class Fox(
    val image: String = "",
    val link: String = "",
)
