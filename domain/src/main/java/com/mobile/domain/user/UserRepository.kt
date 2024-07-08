package com.mobile.domain.user

import com.mobile.domain.model.UserResponseData
import com.mobile.domain.utils.Resource

interface UserRepository
{
    suspend fun fetchAllUsers(): Resource<List<UserResponseData>>

    suspend fun createUser(userResponseData: UserResponseData): Resource<UserResponseData>

    suspend fun getUser(id: String): Resource<UserResponseData>

    suspend fun updateUser(userResponseData: UserResponseData): Resource<UserResponseData>

    suspend fun deleteUser(id: String): Resource<String>

}