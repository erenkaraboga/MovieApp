package com.example.movie_application_eren_karaboga.presentation.movie

import com.example.movie_application_eren_karaboga.base.utils.Result

import com.example.movie_application_eren_karaboga.presentation.search.SearchFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.databinding.FragmentDashboardBinding

import com.example.movie_application_eren_karaboga.presentation.dashboard.DashboardViewModel
import com.example.movie_application_eren_karaboga.presentation.dashboard.adapter.DashboardAdapter


import com.example.movie_application_eren_karaboga.presentation.details.DetailsFragment
import com.example.movie_application_eren_karaboga.presentation.movie.adapter.MovieAdapter


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: DashboardAdapter
    private lateinit var movieAdapterUpComing: DashboardAdapter
    private lateinit var movieAdapterTopRated: DashboardAdapter
    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        listenSearchTap()
        bindViewModel()
        getMovieTypes()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        initRecyclerView()
    }

    private fun listenSearchTap() {
        binding.IvSearch.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, SearchFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    private fun getMovieTypes(){
        viewModel.loadPopularData("1")
        viewModel.loadUpComingData("1")
        viewModel.loadTopRatedData("1")
    }
    private fun bindViewModel() {
        viewModel.getObserverLiveData().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val movieList = result.data!!.results
                    movieAdapter.setList(movieList)
                }
                is Result.Error -> {
                    println(result.message)
                }
            }
        }
        viewModel.getObserverLiveDataUpComing().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val movieList = result.data!!.results
                    movieAdapterUpComing.setList(movieList)
                }
                is Result.Error -> {
                    println(result.message)
                }
            }
        }
        viewModel.getObserverLiveDataTopRated().observe(
            viewLifecycleOwner
        ) { result ->
            when (result) {
                is Result.Success -> {
                    val movieList = result.data!!.results
                    movieAdapterTopRated.setList(movieList)
                }
                is Result.Error -> {
                    println(result.message)
                }
            }
        }
    }


    private fun initRecyclerView() {
        val managerPopular = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        val managerMost = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        val managerUp = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

        val rVPopular = binding.recyclerviewPopular
        val rVMost = binding.recyclerviewMost
        val rVUp = binding.recyclerviewUpcoming


        rVPopular.layoutManager = managerPopular
        rVMost.layoutManager=managerMost
        rVUp.layoutManager=managerUp

        rVPopular.adapter = movieAdapter
        rVMost.adapter = movieAdapterUpComing
        rVUp.adapter = movieAdapterTopRated
    }

    private fun setAdapter() {
        movieAdapter = DashboardAdapter(object : DashboardAdapter.OnClickListener {
            override fun onclick(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
        movieAdapterUpComing =  DashboardAdapter(object : DashboardAdapter.OnClickListener {
            override fun onclick(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
        movieAdapterTopRated =DashboardAdapter(object : DashboardAdapter.OnClickListener {
            override fun onclick(movieId: Int) {
                navigateDetailPage(movieId)
            }
        })
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