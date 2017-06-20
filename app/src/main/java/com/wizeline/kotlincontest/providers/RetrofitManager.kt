package com.wizeline.kotlincontest.providers

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.wizeline.kotlincontest.BuildConfig.API_KEY
import com.wizeline.kotlincontest.models.Response
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager : RetrofitProvider {

    private val BASE_URL = "http://ws.audioscrobbler.com/2.0/"
    private val METHOD = "tag.gettopalbums"
    private val FORMAT = "json"
    private var TAG = "pop"

    private val serviceApi: ServiceAPI by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ServiceAPI::class.java)
    }

    override fun getList(): Observable<Response> {
        return serviceApi.getList(METHOD, API_KEY, FORMAT, TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}