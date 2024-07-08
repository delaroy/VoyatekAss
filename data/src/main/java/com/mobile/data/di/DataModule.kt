package com.mobile.data.di

import com.paypay.data.conversion.CurrencyConversionRepositoryImpl
import com.paypay.data.currencies.CurrencyRepositoryImpl
import com.paypay.domain.conversion.CurrencyConversionRepository
import com.paypay.domain.currencies.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl) : CurrencyRepository

    @Binds
    fun bindCurrencyConversionRepository(currencyConversionRepositoryImpl: CurrencyConversionRepositoryImpl) : CurrencyConversionRepository
}