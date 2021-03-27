package com.delta22.rentateamtest.data

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delta22.rentateamtest.data.json.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "UsersDatabase"

        fun getDatabase(@NonNull context: Context): UsersDatabase {
            return Room.databaseBuilder(
                context,
                UsersDatabase::class.java,
                DB_NAME
            ).build()
        }
    }

    abstract fun dao(): UserDao
}
