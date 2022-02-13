package com.yarenyarsilikal.movieland.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.data.model.response.MoviesResponse
import com.yarenyarsilikal.movieland.data.repository.Repository
import com.yarenyarsilikal.movieland.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val mutableNowPlaying: MutableLiveData<MoviesResponse> = MutableLiveData()
    val nowPlaying: LiveData<MoviesResponse>
        get() = mutableNowPlaying

    private val mutableUpcoming: MutableLiveData<MoviesResponse> = MutableLiveData()
    val upcoming: LiveData<MoviesResponse>
        get() = mutableUpcoming

    private val mutableUpcomingNewPage: MutableLiveData<List<MovieResponse>> = MutableLiveData()
    val upcomingNewPage: LiveData<List<MovieResponse>>
        get() = mutableUpcomingNewPage

    private val mutableMovieItemClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val movieItemClickEvent: LiveData<Event<Int>>
        get() = mutableMovieItemClickEvent

    private var page: Int = 1

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            mutableNowPlaying.value = repository.getNowPlayingMovies()
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            mutableUpcoming.value = repository.getUpcomingMovies()
        }
    }

    fun pageRefreshed() {
        getUpcomingMovies()
        getNowPlayingMovies()
    }

    fun scrolledEndOfPage() {
        viewModelScope.launch {
            if (mutableUpcoming.value?.total_pages ?: 1 >= page) {
                page += 1
                mutableUpcomingNewPage.value = repository.getUpcomingMovies(page).results
            }
        }
    }

    val onMovieItemClicked: (Int) -> Unit = {
        mutableMovieItemClickEvent.value = Event(it)
    }
}