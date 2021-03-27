package com.delta22.rentateamtest.di

import androidx.lifecycle.ViewModelProvider
import com.delta22.rentateamtest.viewmodel.UsersModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UsersModule {

    @Binds
    @Singleton
    fun provideUsersModelFactory(factory: UsersModelFactory): ViewModelProvider.NewInstanceFactory
}
