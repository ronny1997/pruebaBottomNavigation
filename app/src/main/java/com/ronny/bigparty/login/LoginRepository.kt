package com.ronny.bigparty.login

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.ronny.bigparty.core.exception.Failure
import com.ronny.bigparty.core.functional.LoginCallBack
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


interface LoginRepository {


    fun login(user: String, password: String, load: LoginCallBack)

     class Network @Inject constructor(private val auth: FirebaseAuth): LoginRepository{




         override fun login(user: String, password: String, load: LoginCallBack) {

             auth.signInWithEmailAndPassword("user", "password").addOnCompleteListener { task ->
                 when (task.isSuccessful) {
                     true -> {
                         load.onResponse(true)
                     }
                     false -> {
                         load.onResponse(true)
                     }
                 }

             }
         }
     }


}