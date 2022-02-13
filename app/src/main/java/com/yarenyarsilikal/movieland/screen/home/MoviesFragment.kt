package com.yarenyarsilikal.movieland.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.yarenyarsilikal.movieland.R
import com.yarenyarsilikal.movieland.databinding.FragmentMoviesBinding
import com.yarenyarsilikal.movieland.screen.home.adapter.UpComingMovieAdapter
import com.yarenyarsilikal.movieland.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var binding: FragmentMoviesBinding? = null

    private val viewModel: MoviesViewModel by lazy {
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    private lateinit var adapter: UpComingMovieAdapter

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
        obserseUpcomingMovies()
        observeUpcomingMoviesNewPage()
        setListeners()
    }

    private fun setListeners() {
        binding?.nestedScrollView?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.scrolledEndOfPage()
            }
        })
    }

    private fun obserseUpcomingMovies() {
        viewModel.upcoming.observe(viewLifecycleOwner) {
            it?.let {
                adapter = UpComingMovieAdapter(it.results) {
                    viewModel.onMovieItemClicked
                }
                binding?.rvMovies?.adapter = adapter
            }
        }
    }

    private fun observeUpcomingMoviesNewPage() {
        viewModel.upcomingNewPage.observe(viewLifecycleOwner) {
            adapter.addItems(it)
            adapter.notifyDataSetChanged()
        }
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