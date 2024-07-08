package com.mobile.voyatekcoding.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import com.mobile.domain.utils.Resource
import timber.log.Timber

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>
typealias MutableLiveEventResource<T> = MutableLiveData<Event<Resource<T>>>
typealias LiveEventResource<T> = LiveData<Event<Resource<T>>>
typealias MutableLiveResource<T> = MutableLiveData<Resource<T>>
typealias LiveResource<T> = LiveData<Resource<T>>

fun NavController.safelyNavigate(resId: NavDirections, extras:  FragmentNavigator.Extras) =
    try { navigate(resId, extras) }
    catch (e: Exception) { Timber.tag("naverror").d(e.message!!) }