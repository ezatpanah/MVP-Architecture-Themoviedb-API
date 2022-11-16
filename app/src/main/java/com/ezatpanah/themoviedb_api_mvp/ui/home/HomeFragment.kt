package com.ezatpanah.themoviedb_api_mvp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.ezatpanah.themoviedb_api_mvp.adapter.CommonMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.adapter.GenreMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.adapter.UpcomingMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.databinding.FragmentHomeBinding
import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.response.GenresListResponse
import com.ezatpanah.themoviedb_api_mvp.response.UpcomingMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.utils.CheckConnection
import com.ezatpanah.themoviedb_api_mvp.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeContracts.View {

    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter

    @Inject
    lateinit var genreMoviesAdapter: GenreMoviesAdapter

    @Inject
    lateinit var commonMoviesAdapter: CommonMoviesAdapter

    @Inject
    lateinit var homePresenter: HomePresenter

    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    private val viewModel: MoviesViewModel by viewModels()


    private val checkConnection by lazy { CheckConnection(requireActivity().application) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePresenter.callUpcomingMoviesList(1)
        homePresenter.callGenres()
        homePresenter.callPopularMoviesList(1)

    }

    override fun loadUpcomingMoviesList(data: UpcomingMoviesListResponse) {
        binding.apply {
            upcomingMoviesAdapter.differ.submitList(data.results)
            topMoviesRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = upcomingMoviesAdapter
            }
            pagerHelper.attachToRecyclerView(topMoviesRecycler)
            topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerHelper)
        }
        commonMoviesAdapter.setonItemClickListener {
            val direction = HomeFragmentDirections.actionToDetailFragment(it.id)
            findNavController().navigate(direction)
        }
    }

    override fun loadGenres(data: GenresListResponse) {
        binding.apply {
            genreMoviesAdapter.differ.submitList(data.genres)
            genresRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = genreMoviesAdapter
            }
        }
    }

    override fun loadMoviesGenres(data: CommonMoviesListResponse) {

    }

    override fun loadPopularMoviesList(data: CommonMoviesListResponse) {
        binding.apply {

            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect{
                    commonMoviesAdapter.submitData(it)
                }
            }


            lastMoviesRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = commonMoviesAdapter
            }
        }
    }

    override fun responseError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}