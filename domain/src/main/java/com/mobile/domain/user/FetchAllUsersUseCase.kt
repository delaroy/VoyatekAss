package com.mobile.domain.user

import com.mobile.domain.model.UserResponseData
import com.mobile.domain.utils.Resource
import com.mobile.domain.utils.SuspendUseCase
import javax.inject.Inject

class FetchAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<Unit, Resource<List<UserResponseData>>>() {

    override suspend fun invoke(param: Unit): Resource<List<UserResponseData>> =
        userRepository.fetchAllUsers()
}