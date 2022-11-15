package com.ezatpanah.themoviedb_api_mvp.ui.home

import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface  HomeContracts {
    interface View : BaseView {
        fun loadUpcomingMoviesList()
        fun loadGenres()
        fun loadMoviesGenres()
        fun loadPopularMoviesList()
    }

    interface Presenter : BasePresenter {
        fun callUpcomingMoviesList()
        fun callGenres()
        fun callMoviesGenres()
        fun callPopularMoviesList()
    }
}