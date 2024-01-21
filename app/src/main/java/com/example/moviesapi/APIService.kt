package com.example.moviesapi

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @Headers(
        "Accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNzc0ZjIwNjk1OWFmZGM0N2YzYTE0ZGY0N2ZkN2FkNiIsInN1YiI6IjY0N2NiYWM1Y2FlZjJkMDBkZjg5MjFmNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MzbWdv9BlyGc3b9Mk7qK3qjhsqcYBFBjJkW9Lqn4qBc"
    )
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResponse
}