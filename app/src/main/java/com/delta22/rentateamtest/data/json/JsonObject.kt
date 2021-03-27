package com.delta22.rentateamtest.data.json

import com.google.gson.annotations.SerializedName

class JsonObject(
    @SerializedName("data")
    var users: List<User>
)
