package com.delta22.rentateamtest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delta22.rentateamtest.data.json.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun fetchUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<User>)
}
