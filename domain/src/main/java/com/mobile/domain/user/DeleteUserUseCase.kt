package com.mobile.domain.user

import com.mobile.domain.model.DeleteUser
import com.mobile.domain.utils.Resource
import com.mobile.domain.utils.SuspendUseCase
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : SuspendUseCase<String, Resource<DeleteUser>>() {

    override suspend fun invoke(param: String): Resource<DeleteUser> =
        userRepository.deleteUser(param)
}