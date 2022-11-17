package com.ezatpanah.themoviedb_api_mvp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.themoviedb_api_mvp.R
import com.ezatpanah.themoviedb_api_mvp.adapter.CommonMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.databinding.FragmentSearchBinding
import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchContracts.View {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var commonMoviesAdapter: CommonMoviesAdapter

    @Inject
    lateinit var searchPresenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchEdt.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    searchPresenter.callSearchMoviesList(1,search)
                }
            }
        }
    }

    override fun loadSearchMoviesList(data: CommonMoviesListResponse) {
        binding.apply {

            commonMoviesAdapter.bind(data.results)

            moviesRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = commonMoviesAdapter
            }

            commonMoviesAdapter.setonItemClickListener {
                val direction = SearchFragmentDirections.actionToDetailFragment(it.id)
                findNavController().navigate(direction)
            }

        }
    }

    override fun showLoading() {
        binding.apply {
            searchLoading.visibility = View.VISIBLE
            moviesRecycler.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        binding.apply {
            searchLoading.visibility = View.GONE
            moviesRecycler.visibility = View.VISIBLE
        }
    }

    override fun responseError(error: String) {
        TODO("Not yet implemented")
    }

}