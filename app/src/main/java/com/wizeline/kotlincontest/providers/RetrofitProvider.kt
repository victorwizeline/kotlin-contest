package com.wizeline.kotlincontest.providers

import com.wizeline.kotlincontest.models.Response
import io.reactivex.Observable

interface RetrofitProvider {

    fun getList() : Observable<Response>
}