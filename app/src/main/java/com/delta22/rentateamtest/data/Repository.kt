package com.delta22.rentateamtest.data

import com.delta22.rentateamtest.data.json.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(
    retrofit: Retrofit,
    private val database: UsersDatabase
) {
    var usersApi: UsersApi = retrofit.create(UsersApi::class.java)

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
        var isDataSaved = false
    }
}
