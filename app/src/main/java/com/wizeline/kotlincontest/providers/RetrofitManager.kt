package com.wizeline.kotlincontest.providers

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.wizeline.kotlincontest.BuildConfig
import com.wizeline.kotlincontest.models.Response
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager : RetrofitProvider {

    class Params {
        companion object {
            const val BASE_URL = "http://ws.audioscrobbler.com/"
            const val METHOD = "tag.gettopalbums"
            const val FORMAT = "json"
            const val TAG = "pop"
        }
    }

    private val serviceApi: ServiceAPI by lazy {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(logging).build())
                .build().create(ServiceAPI::class.java)
    }

    override fun getList(): Observable<Response> {
        return serviceApi.getList(Params.METHOD, BuildConfig.API_KEY, Params.FORMAT, Params.TAG)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}