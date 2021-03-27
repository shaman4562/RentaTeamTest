package com.delta22.rentateamtest.data.json

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class User(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("avatar")
    var avatar: String,
) : Serializable {

    companion object {
        const val USER_EXTRA = "USER_EXTRA"
    }
}
