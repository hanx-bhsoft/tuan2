package com.hanx.recycleviewtetete.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//that like dao in room
interface ApiService {
    @GET("todos")
    suspend fun getUser():Response<Array<Task>>
}