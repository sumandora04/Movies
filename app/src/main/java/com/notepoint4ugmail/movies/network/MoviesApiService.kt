package com.notepoint4ugmail.movies.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {

    @GET("movie/{movie_type}")
    fun getPopularMovies(
        @Path("movie_type", encoded = true) type:String,
        @Query("api_key") apiKey:String)
            :Deferred<MovieResponse>

}

object MovieApi{
    val retrofitService:MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}

enum class MovieTypes(val value:String){
    SHOW_POPULAR("popular"),
    SHOW_TOP("top_rated"),
    SHOW_UPCOMING("upcoming"),
    SHOW_NOW_PLAYING("now_playing")
}