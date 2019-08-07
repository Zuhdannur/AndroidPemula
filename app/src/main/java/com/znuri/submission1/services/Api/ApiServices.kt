package com.znuri.submission1.services.Api

import com.znuri.submission1.services.model.TeamResponses
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("search_all_teams.php?l=English%20Premier%20League")
    fun getAllTeams() : Observable<TeamResponses>

    @GET("lookupteam.php")
    fun getDetail(@Query("id")id:String?) : Observable<TeamResponses>

    companion object{
        fun create() : ApiServices {
            var client = HttpLoggingInterceptor()
            client.level = HttpLoggingInterceptor.Level.BODY

            var http = OkHttpClient.Builder().addInterceptor(client).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(http)
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}