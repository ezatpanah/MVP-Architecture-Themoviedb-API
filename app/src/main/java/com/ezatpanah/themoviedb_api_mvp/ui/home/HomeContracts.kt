package com.ezatpanah.themoviedb_api_mvp.ui.home

import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.response.GenresListResponse
import com.ezatpanah.themoviedb_api_mvp.response.UpcomingMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface  HomeContracts {
    interface View : BaseView {
        fun loadUpcomingMoviesList(data : UpcomingMoviesListResponse)
        fun loadGenres(data : GenresListResponse)
        fun loadMoviesGenres(data : CommonMoviesListResponse)
        fun loadPopularMoviesList(data : CommonMoviesListResponse)
    }

    interface Presenter : BasePresenter {
        fun callUpcomingMoviesList(page: Int)
        fun callGenres()
        fun callMoviesGenres(page: Int,with_genres: String)
        fun callPopularMoviesList(page: Int)
    }
}