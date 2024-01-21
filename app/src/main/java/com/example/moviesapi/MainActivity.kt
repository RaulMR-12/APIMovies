package com.example.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRecyclerView()
    }

    private fun showRecyclerView(){
        val recyclerView = binding.recyclerViewFilms
        recyclerView.layoutManager =  GridLayoutManager(this, 2)
        CoroutineScope(Dispatchers.IO).launch {
            val filmsList = showMovies()
            runOnUiThread {
                recyclerView.adapter = MovieAdapter(filmsList)
            }
        }
    }

    private suspend fun showMovies() : List<Movie> {
        val filmsList  = mutableListOf<Movie>()

        for (page in 1..50) {
            try {
                val response = apiService.getTopRatedMovies(
                    apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMmMwY2UyZDFkNDVmNWQ2NWYyZDBkZmNkMTQ3YTEyNyIsInN1YiI6IjY1NWUyMWYxMjY2Nzc4MDE0ZTk0OGI0MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.U3i9rJii68EVs9tqbuJ9M1a0KRv4qTqyJSUF_BwI0XQ",
                    language = "en-US",
                    page = page
                )

                val simplifiedList = response.results.map { movie ->
                    Movie(
                        title = movie.title,
                        release_date = movie.release_date.substring(0, 4),
                        overview = movie.overview,
                        poster_path = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${movie.poster_path}"
                    )
                }

                filmsList.addAll(simplifiedList)

            } catch (e: Exception) {
                //e.printStackTrace()
                Log.d("Error: ", e.message.toString())
            }
        }

        return filmsList
    }
}