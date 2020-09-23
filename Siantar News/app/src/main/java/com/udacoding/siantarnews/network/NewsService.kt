package com.udacoding.siantarnews.network

import com.udacoding.siantarnews.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("v2/top-headlines?country=us&category=business&apiKey=137a833f4e3540eab1c1c840d48027ef")
    fun getDataNews(): Call<ResponseServer>
}