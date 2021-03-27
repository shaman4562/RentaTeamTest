package com.delta22.rentateamtest.data

import com.delta22.rentateamtest.data.json.JsonObject
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    fun singleUsers(): Single<JsonObject>
}
