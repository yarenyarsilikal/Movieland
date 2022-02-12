package com.yarenyarsilikal.movieland.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.data.repository.Repository
import com.yarenyarsilikal.movieland.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val mutableMovieDetail: MutableLiveData<MovieResponse> = MutableLiveData()
    val movieDetail: LiveData<MovieResponse>
        get() = mutableMovieDetail

    private val mutableImdbEvent : MutableLiveData<String> = MutableLiveData()
    val imdbEvent : LiveData<String>
        get() = mutableImdbEvent

    fun getMovieDetail(movieId: Int?) {
        movieId?.let {
            viewModelScope.launch {
                mutableMovieDetail.value = repository.getMovieDetail(movieId)
            }
        }
    }

    fun imdbClickEvent (imdbId : String) {
        if (imdbId.isNullOrEmpty().not())
            mutableImdbEvent.value = AppConstants.IMDB_BASE_URL.plus(imdbId)
    }

}