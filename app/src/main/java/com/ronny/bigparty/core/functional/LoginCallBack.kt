package com.ronny.bigparty.core.functional

import com.ronny.bigparty.core.exception.Failure
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


interface  LoginCallBack {

    fun onResponse(response: Boolean)

    fun onFailure(failure: Failure)

}