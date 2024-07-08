package com.mobile.data.user

import com.mobile.data.api.ApiService
import com.mobile.data.di.IoDispatcher
import com.mobile.data.util.safeApiCall
import com.mobile.domain.model.UserResponseData
import com.mobile.domain.user.UserRepository
import com.mobile.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userMapper: UserMapper,
    private val api : ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun fetchAllUsers(): Resource<List<UserResponseData>> =
        withContext(dispatcher) {
            val data = safeApiCall {
                api.getAllUsers()
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { userMapper.mapModelList(it) })
        }

    override suspend fun createUser(userResponseData: UserResponseData): Resource<UserResponseData> =
        withContext(dispatcher) {
            val data = safeApiCall {
                api.createUser(userMapper.to(userResponseData))
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { userMapper.from(it) })
        }

    override suspend fun getUser(id: String): Resource<UserResponseData> =
        withContext(dispatcher) {
            val data = safeApiCall {
                api.getUser(id = id)
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { userMapper.from(it) })
        }
    override suspend fun updateUser(userResponseData: UserResponseData): Resource<UserResponseData> =
        withContext(dispatcher) {
            val data = safeApiCall {
                api.updateUser(id = userResponseData.id!!,  userMapper.to(userResponseData))
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { userMapper.from(it) })
        }

    override suspend fun deleteUser(id: String): Resource<String> =
        withContext(dispatcher) {
            val data = safeApiCall {
                api.deleteUser(id = id)
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let {it.success })
        }
}