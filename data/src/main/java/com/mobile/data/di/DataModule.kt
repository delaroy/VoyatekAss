package com.mobile.data.di

import com.mobile.data.user.UserRepositoryImpl
import com.mobile.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository

}