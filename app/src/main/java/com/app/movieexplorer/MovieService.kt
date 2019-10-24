package com.app.movieexplorer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService{

    @GET("movie/popular?")
    fun getPopularMoviesList(@Query("api_key") apiKey:String,@Query("language") lang:String,@Query("page") page:String):Call<PopularMoviesResponse>
}