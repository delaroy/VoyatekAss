package com.mobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "email") val email: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "address") val address: String,
    @Json(name = "id") val id: String
)