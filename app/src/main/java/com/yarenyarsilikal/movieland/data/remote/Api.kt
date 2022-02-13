package com.yarenyarsilikal.movieland.data.remote

import com.yarenyarsilikal.movieland.BuildConfig.KEY_API
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.data.model.response.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */
interface Api {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String? = KEY_API,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
        @Query("page") page: Int? = 1
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String? = KEY_API,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
        @Query("page") page: Int? = 1
    ): MoviesResponse


    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String? = KEY_API,
        @Query("language") language: String = Locale.getDefault().toLanguageTag(),
    ): MovieResponse
}