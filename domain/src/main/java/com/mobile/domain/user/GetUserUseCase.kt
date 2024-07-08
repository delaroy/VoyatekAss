package com.mobile.domain.user

import com.mobile.domain.model.UserResponseData
import com.mobile.domain.utils.Resource
import com.mobile.domain.utils.SuspendUseCase
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<String, Resource<UserResponseData>>() {

    override suspend fun invoke(param: String): Resource<UserResponseData> =
        userRepository.getUser(param)
}