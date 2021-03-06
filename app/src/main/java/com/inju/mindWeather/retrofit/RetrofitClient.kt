package com.inju.mindWeather.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//RetrofitClient Class with Singleton Pattern
object RetrofitClient {

    //레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String) : Retrofit? {

        if(retrofitClient == null) {
            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(loggingInterceptor)

//            val client = OkHttpClient()
//            client.setConnectTimeout(30, TimeUnit.SECONDS) // connect timeout
//
//            client.setReadTimeout(30, TimeUnit.SECONDS) // socket timeout

            clientBuilder.connectTimeout(5, TimeUnit.MINUTES) // connect
            clientBuilder.writeTimeout(5, TimeUnit.MINUTES) // write
            clientBuilder.readTimeout(5, TimeUnit.MINUTES); // read


            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()
        }

        return retrofitClient
    }
}