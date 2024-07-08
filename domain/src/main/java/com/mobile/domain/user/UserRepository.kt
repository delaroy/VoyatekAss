package com.mobile.domain.user

import com.mobile.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository
{
    suspend fun fetchAllUsers(): Resource<List<UserRes>>

    suspend fun getUser(): Resource<String>

    suspend fun updateUser(): Resource<String>

    suspend fun deleteUser(): Resource<String>

    fun fetchAllConvertedCurrencies(): Flow<List<CurrenciesConvertedData>>
}