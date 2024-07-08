package com.mobile.voyatekcoding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.domain.model.UserResponseData
import com.mobile.domain.user.CreateUserUseCase
import com.mobile.domain.user.DeleteUserUseCase
import com.mobile.domain.user.FetchAllUsersUseCase
import com.mobile.domain.user.GetUserUseCase
import com.mobile.domain.user.UpdateUserUseCase
import com.mobile.domain.utils.Resource
import com.mobile.voyatekcoding.util.Event
import com.mobile.voyatekcoding.util.LiveEventResource
import com.mobile.voyatekcoding.util.MutableLiveEventResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchAllUsersUseCase: FetchAllUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _fetchAllUsersRequest = MutableLiveEventResource<List<UserResponseData>>()
    val fetchAllUsersRequest: LiveEventResource<List<UserResponseData>> = _fetchAllUsersRequest

    private val _fetchUserRequest = MutableLiveEventResource<UserResponseData>()
    val fetchUserRequest: LiveEventResource<UserResponseData> = _fetchUserRequest

    private val _createUserRequest = MutableLiveEventResource<UserResponseData>()
    val createUserRequest: LiveEventResource<UserResponseData> = _createUserRequest

    private val _updateUserRequest = MutableLiveEventResource<UserResponseData>()
    val updateUserRequest: LiveEventResource<UserResponseData> = _updateUserRequest

    private val _deleteUserRequest = MutableLiveEventResource<String>()
    val deleteUserRequest: LiveEventResource<String> = _deleteUserRequest

    fun fetchAllUsers() {
        _fetchAllUsersRequest.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateAllUsersResult = fetchAllUsersUseCase(param = Unit)

            if (initiateAllUsersResult.isSuccess()) {
                _fetchAllUsersRequest.value = Event(Resource.success(initiateAllUsersResult.data))
            } else {
                _fetchAllUsersRequest.value =
                    Event(Resource.error(message = initiateAllUsersResult.message))
            }
        }
    }

    fun fetchUser(id: String) {
        _fetchUserRequest.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateFetchUserResult = getUserUseCase(param = id)

            if (initiateFetchUserResult.isSuccess()) {
                _fetchUserRequest.value = Event(Resource.success(initiateFetchUserResult.data))
            } else {
                _fetchUserRequest.value =
                    Event(Resource.error(message = initiateFetchUserResult.message))
            }
        }
    }

    fun createUser(userResponseData: UserResponseData) {
        _createUserRequest.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateCreateUserResult = createUserUseCase(param = userResponseData)

            if (initiateCreateUserResult.isSuccess()) {
                _createUserRequest.value = Event(Resource.success(initiateCreateUserResult.data))
            } else {
                _createUserRequest.value =
                    Event(Resource.error(message = initiateCreateUserResult.message))
            }
        }
    }

    fun updateUser(userResponseData: UserResponseData) {
        _updateUserRequest.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateUpdateUserResult = updateUserUseCase(param = userResponseData)

            if (initiateUpdateUserResult.isSuccess()) {
                _updateUserRequest.value = Event(Resource.success(initiateUpdateUserResult.data))
            } else {
                _updateUserRequest.value =
                    Event(Resource.error(message = initiateUpdateUserResult.message))
            }
        }
    }

    fun deleteUser(id: String) {
        _deleteUserRequest.value = Event(Resource.loading())
        viewModelScope.launch {
            val initiateDeleteUserResult = deleteUserUseCase(param = id)

            if (initiateDeleteUserResult.isSuccess()) {
                _deleteUserRequest.value = Event(Resource.success(initiateDeleteUserResult.data))
            } else {
                _deleteUserRequest.value =
                    Event(Resource.error(message = initiateDeleteUserResult.message))
            }
        }
    }

}