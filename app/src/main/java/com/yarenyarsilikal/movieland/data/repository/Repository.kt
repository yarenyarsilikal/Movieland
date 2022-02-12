package com.yarenyarsilikal.movieland.data.repository

import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.data.model.response.MoviesResponse
import com.yarenyarsilikal.movieland.data.remote.Api
import javax.inject.Inject


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */

class Repository @Inject constructor(private val api: Api) {

    suspend fun getUpcomingMovies(): MoviesResponse = api.getUpcomingMovies()

    suspend fun getNowPlayingMovies(): MoviesResponse = api.getNowPlaying()

    suspend fun getMovieDetail(movieId: Int): MovieResponse = api.getMovieDetail(id = movieId)
}