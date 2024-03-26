package com.example.tmdbclient.presentation.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.databinding.FragmentTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
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
        (viewLifecycleOwner as Injector).createTvShowSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(TvShowViewModel::class.java)
    }

    private fun initRecyclerView() {
        binding.rvTvShows.layoutManager = LinearLayoutManager(context)
        adapter = TvShowAdapter()
        binding.rvTvShows.adapter = adapter
    }

    private fun initObserver() {
        binding.pbTvShow.visibility = View.VISIBLE
        val responseLiveData = viewModel.getTvShows()
        responseLiveData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.pbTvShow.visibility = View.GONE
            } else {
                binding.pbTvShow.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }
}