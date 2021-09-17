package com.ronny.bigparty.login

import androidx.lifecycle.ViewModel
import com.ronny.bigparty.core.functional.LoginCallBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor (private val onLoginFirebase: OnLoginFirebase) : ViewModel() {

    fun login(user: String, password: String, loginCallBack: LoginCallBack?) {
        onLoginFirebase(OnLoginFirebase.Params(user, password, loginCallBack!!))

    }
}


