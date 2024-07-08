package com.mobile.domain.user

import com.mobile.domain.model.UserResponseData
import com.mobile.domain.utils.Resource
import com.mobile.domain.utils.SuspendUseCase
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<UserResponseData, Resource<UserResponseData>>() {

    override suspend fun invoke(param: UserResponseData): Resource<UserResponseData> =
        userRepository.updateUser(param)
}