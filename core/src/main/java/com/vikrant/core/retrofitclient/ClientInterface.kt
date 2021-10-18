package com.vikrant.core.retrofitclient

import com.vikrant.core.model.Events
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientInterface {
    @GET("events?")
    suspend fun getEvents(@Query("client_id") clientId: String): Events
}