package com.delta22.rentateamtest.data

import android.content.Context
import com.delta22.rentateamtest.data.json.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Repository @Inject constructor(context: Context) {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    var usersApi: UsersApi = retrofit.create(UsersApi::class.java)
    private val database = UsersDatabase.getDatabase(context)

    fun loadUsers(): Single<List<User>> {
        return if (isDataSaved) {
            database.dao()
                .fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            usersApi.singleUsers()
                .map { it.users }
                .doOnSuccess { saveUsersInDatabase(it) }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun saveUsersInDatabase(users: List<User>) {
        database.dao().insertUsers(users)
        isDataSaved = true
    }

    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
        var isDataSaved = false
    }
}
