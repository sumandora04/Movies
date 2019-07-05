package com.notepoint4ugmail.movies.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.notepoint4ugmail.movies.databinding.MovieGridCellBinding
import com.notepoint4ugmail.movies.network.Movie

class OverviewAdapter(private val movieClickListener: MovieClickListener): ListAdapter<Movie,OverviewAdapter.MovieHolder>(MovieDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieItem = getItem(position)

        holder.itemView.setOnClickListener {
            movieClickListener.onClick(movieItem)
        }

        holder.bind(movieItem)
    }

    class MovieHolder(private val binding:MovieGridCellBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem:Movie){
            binding.movieData = movieItem
            binding.executePendingBindings()

            Glide.with(binding.gridImageCell.context)
                .load(movieItem.posterPath)
                .into(binding.gridImageCell)
        }

        companion object{
            fun from(parent: ViewGroup):MovieHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieGridCellBinding.inflate(layoutInflater,parent,false)
                return MovieHolder(binding)
            }
        }
    }

    class MovieDiffCallBack:DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
           return oldItem == newItem
        }

    }


    //For click listener:
    class MovieClickListener(private val clickListener:(movie:Movie)->Unit){
        fun onClick(selectedMovie:Movie)= clickListener(selectedMovie)
    }
}