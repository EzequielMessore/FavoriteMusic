package com.br.favoritemusic.data.net

import com.br.favoritemusic.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
object ServiceGenerator {

    private val BASE_URL = "https://itunes.apple.com"

    private var retrofitBuilder: Retrofit.Builder

    init {
        val gson = GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .create()

        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    fun <T> getService(serviceClass: Class<T>): T = retrofitBuilder.build().create(serviceClass)

}