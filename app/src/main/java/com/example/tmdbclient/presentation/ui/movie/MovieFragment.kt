package com.example.tmdbclient.presentation.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.databinding.FragmentMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViewModel()
        initRecyclerView()
        initObserver()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        (viewLifecycleOwner as Injector).createMovieSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)
    }

    private fun initRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter()
        binding.rvMovies.adapter = adapter
    }

    private fun initObserver() {
        binding.pbMovie.visibility = View.VISIBLE
        val responseLiveData = viewModel.getMovies()
        responseLiveData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.pbMovie.visibility = View.GONE
            } else {
                binding.pbMovie.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }
}