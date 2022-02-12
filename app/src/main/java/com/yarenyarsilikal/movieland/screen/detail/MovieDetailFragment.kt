package com.yarenyarsilikal.movieland.screen.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yarenyarsilikal.movieland.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(this)[MovieDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.viewModel = viewModel
        val movieId = arguments?.getInt("movieId")
        viewModel.getMovieDetail(movieId)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMovieDetail()
        observeImdbEvent()
    }

    private fun observeImdbEvent() {
        viewModel.imdbEvent.observe(viewLifecycleOwner){
            it?.let {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }
    }

    private fun observeMovieDetail() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding?.movie = it
        }
    }



}