package com.yarenyarsilikal.movieland.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yarenyarsilikal.movieland.R
import com.yarenyarsilikal.movieland.databinding.FragmentMoviesBinding
import com.yarenyarsilikal.movieland.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var binding: FragmentMoviesBinding? = null

    private val viewModel: MoviesViewModel by lazy {
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.viewModel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNowPlayingMovies()
        viewModel.getUpcomingMovies()
        observeItemClick()
    }

    private fun observeItemClick() {
        viewModel.movieItemClickEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandledOrReturnNull()?.let { movieId ->
                navigate(
                    R.id.action_moviesFragment_to_movieDetailFragment,
                    bundleOf(Pair("movieId", movieId))
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}