package com.example.movie_application_eren_karaboga.presentation.search
import android.content.Context
import com.example.movie_application_eren_karaboga.presentation.search.adapter.SearchAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var movieAdapter: SearchAdapter
    private lateinit var manager: InputMethodManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        initRecyclerView()
        listenSearch()
        bindViewModel()

        binding.editText.requestFocus()
        manager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT)
        super.onViewCreated(view, savedInstanceState)
    }
    private fun listenSearch(){
        binding.editText.doOnTextChanged { text, _, _, _ ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(searchDelay)
                text?.let {
                    if (it.length >= 2) {
                        viewModel.searchMovie(it.toString())
                    }
                    else if(it.isEmpty()){
                        movieAdapter.setList(arrayListOf())
                    }
                }
            }
        }
    }
  private fun  initRecyclerView(){
      val manager = LinearLayoutManager(
          context,
          LinearLayoutManager.VERTICAL, false
      )
      binding.recyclerview.layoutManager = manager
      binding.recyclerview.adapter = movieAdapter
    }
    private fun setAdapter() {
        movieAdapter =SearchAdapter()
    }
   private fun bindViewModel(){
       viewModel.getObserverLiveData().observe(viewLifecycleOwner) { result ->
           result?.let {
              movieAdapter.setList(it.results)
           }
       }
    }
}