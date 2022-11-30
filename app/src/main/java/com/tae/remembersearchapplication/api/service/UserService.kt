package com.tae.remembersearchapplication.api.service

import com.tae.remembersearchapplication.api.data.UserRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(SEARCH_USER)
    suspend fun search(
        @Query("q") q: String,
//        @Query("sort") sort: String = "desc",
        @Query("page") page: Int=1,
        @Query("per_page") perPage: Int=100
    ): Response<UserRes>

    companion object{
        const val SEARCH_USER="search/users"
    }
}