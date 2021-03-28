package com.delta22.rentateamtest.di

import android.content.Context
import com.delta22.rentateamtest.viewmodel.UsersModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [UsersModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {

    fun getUsersModelFactory(): UsersModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
