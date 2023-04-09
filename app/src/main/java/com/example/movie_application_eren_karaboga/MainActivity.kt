package com.example.movie_application_eren_karaboga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.databinding.ActivityMainBinding
import com.example.movie_application_eren_karaboga.domain.models.Movie
import com.example.movie_application_eren_karaboga.presentation.movie.MovieFragment
import com.example.movie_application_eren_karaboga.presentation.movie.MovieViewModel
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Call

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private  lateinit var  movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieFragment = MovieFragment()
        val fragmentManager : FragmentManager=supportFragmentManager
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.activity_main,movieFragment).commit()

    }
}