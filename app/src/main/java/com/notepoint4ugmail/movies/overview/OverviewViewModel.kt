package com.notepoint4ugmail.movies.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notepoint4ugmail.movies.BuildConfig
import com.notepoint4ugmail.movies.network.Movie
import com.notepoint4ugmail.movies.network.MovieApi
import com.notepoint4ugmail.movies.network.MovieTypes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class OverviewViewModel : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    private val _onNavigationToDetail = MutableLiveData<Movie>()
    val onNavigationToDetail: LiveData<Movie>
        get() = _onNavigationToDetail


    private val viewModelJob = Job()
    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getMovies(MovieTypes.SHOW_NOW_PLAYING)
    }

    private fun getMovies(filter: MovieTypes) {
        scope.launch {
            val deferredMovieList =
                MovieApi.retrofitService.getPopularMovies(filter.value, BuildConfig.MOVIE_DB_API_KEY)
            try {
                val movieResponseList = deferredMovieList.await()
                _moviesList.value = movieResponseList.results
                Log.d("OverviewModel", movieResponseList.results.toString())
            } catch (e: Exception) {
                _moviesList.value = ArrayList()
                Log.d("OverviewModel", e.message)
            }
        }
    }


    fun navigateToDisplay(movie: Movie) {
        _onNavigationToDetail.value = movie
    }

    fun navigateToDisplayComplete() {
        _onNavigationToDetail.value = null
    }


    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    fun updateSelectedMovieType(filter: MovieTypes){
        getMovies(filter)
    }
}