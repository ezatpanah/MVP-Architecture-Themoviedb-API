package com.ezatpanah.themoviedb_api_mvp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.themoviedb_api_mvp.R
import com.ezatpanah.themoviedb_api_mvp.adapter.FavoriteMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.databinding.FragmentFavoritesBinding
import com.ezatpanah.themoviedb_api_mvp.db.MoviesEntity
import com.ezatpanah.themoviedb_api_mvp.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() , FavoritesContracts.View {


    private lateinit var binding: FragmentFavoritesBinding

    @Inject
    lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    @Inject
    lateinit var favoritesPresenter: FavoritesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesPresenter.callFavoritesList()

    }

    override fun loadFavoriteMovieList(data: MutableList<MoviesEntity>) {

        favoriteMoviesAdapter.bind(data)

        binding.favoriteRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteMoviesAdapter
        }

        favoriteMoviesAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionToDetailFragment(it.id)
            findNavController().navigate(direction)
        }

    }

    override fun showEmpty() {
        binding.apply {
            emptyItemsLay.visibility = View.VISIBLE
            favoriteRecycler.visibility = View.GONE
        }
    }

    override fun showLoading() {
        binding.apply {
            favLoading.visibility = View.VISIBLE
            favoriteRecycler.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        binding.apply {
            favLoading.visibility = View.GONE
            favoriteRecycler.visibility = View.VISIBLE
        }
    }

    override fun responseError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()

    }


    override fun onDestroy() {
        super.onDestroy()
        favoritesPresenter.onStop()
    }


}