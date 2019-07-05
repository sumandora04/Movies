package com.notepoint4ugmail.movies.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.notepoint4ugmail.movies.network.MovieResponse
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val movieResponse: MovieResponse,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(movieResponse,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel found")
    }
}