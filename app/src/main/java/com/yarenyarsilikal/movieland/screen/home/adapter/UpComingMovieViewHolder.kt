package com.yarenyarsilikal.movieland.screen.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.databinding.ItemMovieBinding

/**
 * Created by yarenyarsilikal on 13.02.2022.
 */
class UpComingMovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movieModel: MovieResponse,
        onMovieClickListener: ((Int) -> Unit)?
    ) {
        binding.movie = movieModel
        itemView.setOnClickListener {
            onMovieClickListener?.invoke(movieModel.id)
        }
    }

}