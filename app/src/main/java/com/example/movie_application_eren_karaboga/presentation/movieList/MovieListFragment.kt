package com.example.movie_application_eren_karaboga.presentation.movieList

import com.example.movie_application_eren_karaboga.base.utils.Result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.data.models.MovieResult
import com.example.movie_application_eren_karaboga.databinding.FragmentMovieListBinding
import com.example.movie_application_eren_karaboga.presentation.dashboard.DashboardViewModel
import com.example.movie_application_eren_karaboga.presentation.details.DetailsFragment
import com.example.movie_application_eren_karaboga.presentation.movieList.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieListAdapter
    private val viewModel by viewModels<DashboardViewModel>()
    private var type: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        listenBackTap()
        arguments?.let {
            type = it.getString(Constants.MOVIE_TYPE).toString()
        }
        bindViewModel()
        getMovies()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        initRecyclerView()
    }


    private fun bindViewModel() {
        viewModel.getObserverLiveData(type).observe(
            viewLifecycleOwner
        ) { result ->
            handleResult(result, movieAdapter)
        }
    }

    private fun handleResult(
        result: Result<MovieResult>?,
        adapter: MovieListAdapter
    ) {
        when (result) {
            is Result.Success -> {
                val movieList = result.data!!.results
                adapter.setList(movieList)
            }
            is Result.Error -> {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun setAdapter() {
        movieAdapter = MovieListAdapter(object : MovieListAdapter.OnClickListener {
            override fun onclick(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
    }
    private fun getMovies(){
      viewModel.loadMovieData(type)
    }
    private fun initRecyclerView() {
        val managerPopular = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerview.layoutManager = managerPopular
        binding.recyclerview.adapter = movieAdapter
    }
    private fun listenBackTap() {
        binding.IvBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun navigateDetailPage(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.MOVIE_ID, movieId)
        val movieDetailFragment = DetailsFragment()
        movieDetailFragment.arguments = bundle
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main, movieDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}