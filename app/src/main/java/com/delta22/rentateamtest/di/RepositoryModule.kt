package com.delta22.rentateamtest.di

import android.content.Context
import com.delta22.rentateamtest.data.UsersDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RepositoryModule {

    private const val BASE_URL = "https://reqres.in/api/"

    @JvmStatic
    @Provides
    fun provideDatabase(context: Context): UsersDatabase {
        return UsersDatabase.getDatabase(context)
    }

    @JvmStatic
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}
