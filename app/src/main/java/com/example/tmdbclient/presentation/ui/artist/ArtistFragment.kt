package com.example.tmdbclient.presentation.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.databinding.FragmentArtistBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var viewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding.inflate(inflater, container, false)
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
        (viewLifecycleOwner as Injector).createArtistSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)
    }

    private fun initRecyclerView() {
        binding.rvArtists.layoutManager = LinearLayoutManager(context)
        adapter = ArtistAdapter()
        binding.rvArtists.adapter = adapter
    }

    private fun initObserver() {
        binding.pbArtist.visibility = View.VISIBLE
        val responseLiveData = viewModel.getArtists()
        responseLiveData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.pbArtist.visibility = View.GONE
            } else {
                binding.pbArtist.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }
}