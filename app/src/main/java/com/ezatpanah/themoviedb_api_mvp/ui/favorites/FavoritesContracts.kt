package com.ezatpanah.themoviedb_api_mvp.ui.favorites

import com.ezatpanah.themoviedb_api_mvp.db.MoviesEntity
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface  FavoritesContracts {

    interface View : BaseView {
        fun loadFavoriteMovieList(data: MutableList<MoviesEntity>)
        fun showEmpty()
    }

    interface Presenter : BasePresenter {
        fun callFavoritesList()
    }

}