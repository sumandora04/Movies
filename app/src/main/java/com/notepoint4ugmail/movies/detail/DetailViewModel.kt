package com.notepoint4ugmail.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.notepoint4ugmail.movies.R
import com.notepoint4ugmail.movies.network.Movie

class DetailViewModel(movie: Movie,application: Application):AndroidViewModel(application) {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie:LiveData<Movie>
    get() = _selectedMovie

    init {
        _selectedMovie.value = movie
    }

    val movieTitle = Transformations.map(selectedMovie){
        it?.movieTitle
    }

    val movieReleaseDate = Transformations.map(selectedMovie){
        application.applicationContext.getString(R.string.release_date_format,it?.releasedDate)
    }

    val movieLanguage = Transformations.map(selectedMovie){
        application.applicationContext.getString(R.string.language_format, it?.movieLanguage)
    }

    val movieRating = Transformations.map(selectedMovie){
        application.applicationContext.getString(R.string.rating_format,it?.voteRating.toString())
    }

    val movieOverview = Transformations.map(selectedMovie){
        application.applicationContext.getString(R.string.overview_format,it?.movieOverview)
    }
}