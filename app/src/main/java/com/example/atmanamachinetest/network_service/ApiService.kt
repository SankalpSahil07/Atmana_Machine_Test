package com.example.atmanamachinetest.network_service

import com.example.atmanamachinetest.model.data.ReqResData
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiService{


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    val api : RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

}


enum class NetworkStatus { LOADING, ERROR, DONE }

//private const val BASE_URL = "https://reqres.in/api/users?page=1"
private const val BASE_URL = "https://reqres.in/api/"


interface RetrofitService {

    @GET("users")
    suspend fun getRequestResponseData(
        @Query("page") page:Int
    ) : ReqResData
}

