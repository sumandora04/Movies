package com.notepoint4ugmail.movies.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name ="id") val id:Double,
    @Json(name ="title") val movieTitle:String,
    @Json(name="poster_path") val posterPath:String,
    @Json(name="original_language") val movieLanguage: String,
    @Json(name="release_date") val releasedDate: String,
    @Json(name="overview") val movieOverview: String,
    @Json(name="vote_average") val voteRating: Double
):Parcelable