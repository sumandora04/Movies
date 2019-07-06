package com.notepoint4ugmail.movies

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.notepoint4ugmail.movies.network.Movie
import com.notepoint4ugmail.movies.overview.OverviewAdapter


@BindingAdapter("movieRecycler")
fun RecyclerView.recyclerBinder(movie:List<Movie>?){
    movie?.let {
        val adapter = this.adapter as OverviewAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("imageBinding")
fun ImageView.imageBinder(imageUrl:String?){
    imageUrl?.let {
        val finalImageUrl = "https://image.tmdb.org/t/p/w500"+imageUrl
        val imageUri = finalImageUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(this.context)
            .load(imageUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.broken_img))
            .into(this)
    }
}