package com.example.movie_application_eren_karaboga.presentation.search
import com.example.movie_application_eren_karaboga.presentation.search.adapter.SearchAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.databinding.FragmentSearchBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: MovieSearch by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var searchJob: Job? = null
    private val searchDelay: Long = 500

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.editText.requestFocus()
        listenSearch()
        bindViewModel()
        return binding.root
    }
    fun listenSearch(){
        binding.editText.doOnTextChanged { text, _, _, _ ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(searchDelay)
                text?.let {
                    if (it.length >= 2) {
                        viewModel.searchmovie(it.toString())
                    }
                }
            }
        }
    }
   fun bindViewModel(){
       viewModel.getObserverLiveData().observe(viewLifecycleOwner) { result ->
           result?.let {
               val manager = LinearLayoutManager(
                   context,
                   LinearLayoutManager.VERTICAL, false
               )
               binding.recyclerview.layoutManager = manager
               binding.recyclerview.adapter = SearchAdapter(it.results)
           }
       }
    }
}