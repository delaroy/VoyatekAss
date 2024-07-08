package com.mobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponseData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val gender: String,
    val address: String,
    val id: String? = null
): Parcelable