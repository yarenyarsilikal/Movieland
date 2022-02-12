package com.yarenyarsilikal.movieland.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarenyarsilikal.movieland.data.model.response.MoviesResponse
import com.yarenyarsilikal.movieland.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val mutableNowPlaying: MutableLiveData<MoviesResponse> = MutableLiveData()
    val nowPlaying: LiveData<MoviesResponse>
        get() = mutableNowPlaying

    fun getUpcomingMovies() {
        viewModelScope.launch {
            mutableNowPlaying.value = repository.getUpcomingMovies()
        }
    }
}