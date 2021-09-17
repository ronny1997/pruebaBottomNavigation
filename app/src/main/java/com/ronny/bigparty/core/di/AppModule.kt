package com.ronny.bigparty.core.di

import com.google.firebase.auth.FirebaseAuth
import com.ronny.bigparty.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebase(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRepository(auth: FirebaseAuth): LoginRepository {
        return LoginRepository.Network(auth)
    }


}