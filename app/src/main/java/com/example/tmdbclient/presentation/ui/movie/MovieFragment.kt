package com.example.tmdbclient.presentation.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.data.model.movie.Movie
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
        swipeListener()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        (activity?.application as Injector).createMovieSubComponent()
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
            it?.let { movies -> setMoviesToAdapter(movies) }
        }
    }

    private fun swipeListener() {
        binding.swipeMovies.setOnRefreshListener {
            val responseLiveData = viewModel.updateMovies()
            responseLiveData.observe(viewLifecycleOwner) {
                it?.let { movies -> setMoviesToAdapter(movies) }
                binding.swipeMovies.isRefreshing = false
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setMoviesToAdapter(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            adapter.setList(movies)
            adapter.notifyDataSetChanged()
            binding.pbMovie.visibility = View.GONE
        } else {
            binding.pbMovie.visibility = View.GONE
            Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
        }
    }

}