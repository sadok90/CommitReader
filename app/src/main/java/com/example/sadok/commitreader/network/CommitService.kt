package com.example.sadok.commitreader.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



interface CommitService {
    @GET("commits")
    fun getCommits(): Deferred<List<Container>>
}

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


object Network {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/repos/torvalds/linux/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val retrofitService : CommitService by lazy {
        retrofit.create(CommitService::class.java)
    }
}