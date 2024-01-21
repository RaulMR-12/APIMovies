package com.example.moviesapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(var movies : List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, MovieData::class.java)
            intent.putExtra("synopsis", item.overview)
            intent.putExtra("poster_url", item.poster_path)
            context.startActivity(intent)
        }
    }
}