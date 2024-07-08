package com.mobile.data.user

import com.mobile.data.model.UserResponse
import com.mobile.data.util.Mapper
import com.mobile.domain.model.UserResponseData
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserResponse, UserResponseData> {

    override fun to(domain: UserResponseData): UserResponse = domain.run {
        UserResponse(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            gender = gender,
            id = id ?: ""
        )
    }

    override fun from(entity: UserResponse): UserResponseData = entity.run {
        UserResponseData(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            gender = gender,
            id = id
        )
    }
}