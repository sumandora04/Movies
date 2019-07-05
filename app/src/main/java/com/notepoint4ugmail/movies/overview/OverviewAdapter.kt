package com.notepoint4ugmail.movies.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.notepoint4ugmail.movies.databinding.MovieGridCellBinding
import com.notepoint4ugmail.movies.network.MovieResponse

class OverviewAdapter: ListAdapter<MovieResponse,OverviewAdapter.MovieHolder>(MovieDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
    }

    class MovieHolder(private val binding:MovieGridCellBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem:MovieResponse){
            binding.movieData = movieItem
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):MovieHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieGridCellBinding.inflate(layoutInflater,parent,false)
                return MovieHolder(binding)
            }
        }
    }

    class MovieDiffCallBack:DiffUtil.ItemCallback<MovieResponse>(){
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}