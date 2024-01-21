package com.example.moviesapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesapi.databinding.MovieDataLayoutBinding

class MovieData : AppCompatActivity() {

    private lateinit var binding: MovieDataLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDataLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val synopsis = intent.getStringExtra("synopsis")
        val posterUrl = intent.getStringExtra("poster_url")

        binding.synopsisData.text = synopsis
        Glide.with(this).load(posterUrl).into(binding.poster)

        binding.backButton.setOnClickListener { finish() }
    }
}