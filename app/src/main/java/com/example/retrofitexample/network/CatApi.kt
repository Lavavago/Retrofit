package com.example.retrofitexample.network

import com.example.retrofitexample.model.Cat
import retrofit2.http.GET

interface CatApi {
    @GET("images/search")
    suspend fun getRandomCat(): List<Cat>
}