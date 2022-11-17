package com.ezatpanah.themoviedb_api_mvp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ezatpanah.themoviedb_api_mvp.R
import com.ezatpanah.themoviedb_api_mvp.adapter.ImagesAdapter
import com.ezatpanah.themoviedb_api_mvp.databinding.FragmentDetailsBinding
import com.ezatpanah.themoviedb_api_mvp.db.MoviesEntity
import com.ezatpanah.themoviedb_api_mvp.response.CreditsLisResponse
import com.ezatpanah.themoviedb_api_mvp.response.DetailsMovieResponse
import com.ezatpanah.themoviedb_api_mvp.ui.home.HomePresenter
import com.ezatpanah.themoviedb_api_mvp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsContracts.View {

    private lateinit var binding: FragmentDetailsBinding

    private var movieId = 0
    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var entity: MoviesEntity

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            movieId = args.movieId
            if (movieId > 0) {
                detailsPresenter.callDetailsMovie(movieId)
                detailsPresenter.callCreditsMovie(movieId)
            }


            backImg.setOnClickListener {
                findNavController().navigateUp()
            }
        }


    }

    override fun loadDetailsMovie(data: DetailsMovieResponse) {
        binding.apply {
            val moviePosterURL = Constants.POSTER_BASE_URL + data.posterPath
            posterBigImg.load(moviePosterURL)
            posterNormalImg.load(moviePosterURL) {
                crossfade(true)
                crossfade(800)
            }
            movieNameTxt.text = data.title
            movieRateTxt.text = data.voteAverage.toString()
            movieTimeTxt.text = data.runtime.toString()
            movieDateTxt.text = data.releaseDate
            movieSummaryInfo.text = data.overview
        }
    }

    override fun loadCreditsMovie(data: CreditsLisResponse) {
        binding.apply {
            imagesAdapter.differ.submitList(data.cast)
            imagesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = imagesAdapter
            }
        }
    }

    override fun showLoading() {
        binding.apply {
            detailLoading.visibility = View.VISIBLE
            detailScrollView.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        binding.apply {
            detailLoading.visibility = View.GONE
            detailScrollView.visibility = View.VISIBLE
        }
    }

    override fun responseError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsPresenter.onStop()
    }


}