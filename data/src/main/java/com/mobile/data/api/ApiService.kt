package com.mobile.data.api

import com.mobile.data.api.EndPoint.CREATE_USER
import com.mobile.data.api.EndPoint.DELETE_USER
import com.mobile.data.api.EndPoint.GET_ALL_USERS
import com.mobile.data.api.EndPoint.GET_USER
import com.mobile.data.api.EndPoint.UPDATE_USER
import com.mobile.data.model.DeleteMessage
import com.mobile.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET(GET_ALL_USERS)
    suspend fun getAllUsers(): Response<List<UserResponse>>

    @GET(GET_USER)
    suspend fun getUser(
        @Path("id") id: String
    ): Response<UserResponse>

    @POST(CREATE_USER)
    suspend fun createUser(
        @Body body: UserResponse
    ): Response<UserResponse>

    @PUT(UPDATE_USER)
    suspend fun updateUser(
        @Path("id") id: String, @Body body: UserResponse
    ): Response<UserResponse>

    @DELETE(DELETE_USER)
    suspend fun deleteUser(
        @Path("id") id: String
    ): Response<DeleteMessage>
}