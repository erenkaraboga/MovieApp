package com.example.movie_application_eren_karaboga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.domain.models.Movie
import com.example.movie_application_eren_karaboga.presentation.movie.MovieViewModel
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Call

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  lateinit var  movieAdapter : MovieAdapter
    val viewModel by lazy {
         ViewModelProvider(this,defaultViewModelProviderFactory).get(MovieViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        movieAdapter=MovieAdapter()
        recyclerView.adapter =movieAdapter
        viewModel.getObserverLiveData().observe(this, object: Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t!=null){
                    movieAdapter.setList(t.results);
                }
            }

        })
        viewModel.loadPopularData("1");
    }
}