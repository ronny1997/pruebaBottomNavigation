package com.ronny.bigparty.login

import com.ronny.bigparty.core.functional.LoginCallBack
import com.ronny.bigparty.core.interactor.UseCaseWithout
import javax.inject.Inject

class OnLoginFirebase  @Inject constructor(private val loginRepository: LoginRepository) : UseCaseWithout<OnLoginFirebase.Params>() {
    override suspend fun run(params: Params) = loginRepository.login(params.user, params.password , params.loginCallBack)
    class Params(val  user: String, val password: String, val loginCallBack: LoginCallBack)
}