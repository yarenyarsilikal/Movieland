package com.yarenyarsilikal.movieland.data.model.response

data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)