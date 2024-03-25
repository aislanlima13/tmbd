package com.example.tmdbclient.presentation.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.tmdbclient.databinding.FragmentHomeBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieViewModel =
            ViewModelProvider(this).get(MovieViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        movieViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        (context as Injector).createMovieSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        viewModel.getMovies()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}