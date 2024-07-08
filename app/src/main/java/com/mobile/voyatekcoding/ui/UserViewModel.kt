package com.mobile.voyatekcoding.ui

import androidx.lifecycle.ViewModel
import com.mobile.domain.user.DeleteUserUseCase
import com.mobile.domain.user.FetchAllUsersUseCase
import com.mobile.domain.user.GetUserUseCase
import com.mobile.domain.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchAllUsersUseCase: FetchAllUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {


}