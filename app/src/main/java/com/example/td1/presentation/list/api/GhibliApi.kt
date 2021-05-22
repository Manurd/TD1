package com.example.td1.presentation.list.api

import com.example.td1.presentation.list.Ghibli
import retrofit2.Call
import retrofit2.http.GET

interface GhibliApi{
    @GET("films")
    fun getGhibliList(): Call<List<Ghibli>>
}