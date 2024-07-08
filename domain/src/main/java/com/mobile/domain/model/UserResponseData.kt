package com.mobile.domain.model

data class UserResponseData(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val gender: String,
    val address: String,
    val id: String? = null
)