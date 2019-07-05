package com.notepoint4ugmail.movies.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notepoint4ugmail.movies.BuildConfig
import com.notepoint4ugmail.movies.network.Movie
import com.notepoint4ugmail.movies.network.MovieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class OverviewViewModel:ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList:LiveData<List<Movie>>
    get() = _moviesList


    private val viewModelJob = Job()
    private val scope = CoroutineScope(viewModelJob+Dispatchers.Main)


    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        Log.d("OverviewModel","first")
        scope.launch {
            Log.d("OverviewModel","2nd")
            val deferredMovieList = MovieApi.retrofitService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY)

            try {
                Log.d("OverviewModel","3rd")
                val movieResponseList = deferredMovieList.await()
                Log.d("OverviewModel","4th")
                _moviesList.value = movieResponseList.results
                Log.d("OverviewModel",movieResponseList.results.toString())
            } catch (e: Exception) {
                _moviesList.value = ArrayList()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }
}