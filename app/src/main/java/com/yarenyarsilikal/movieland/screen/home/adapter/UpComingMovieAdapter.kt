package com.yarenyarsilikal.movieland.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.databinding.ItemMovieBinding


/**
 * Created by yarenyarsilikal on 13.02.2022.
 */
class UpComingMovieAdapter(
    private var list: List<MovieResponse>?,
    private val onMovieClickListener: ((Int) -> Unit)?
) : RecyclerView.Adapter<UpComingMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingMovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        return UpComingMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpComingMovieViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it, onMovieClickListener) }
    }

    override fun getItemCount(): Int = 10
        //list?.size ?: 0
}