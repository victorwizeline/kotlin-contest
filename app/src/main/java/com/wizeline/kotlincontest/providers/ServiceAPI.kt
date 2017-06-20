package com.wizeline.kotlincontest.providers

import com.wizeline.kotlincontest.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    @GET("./")
    fun getList(@Query("method") method: String, @Query("api_key") api_key: String, @Query("format") format: String, @Query("tag") tag: String): Observable<Response>
}