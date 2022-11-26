package com.ezatpanah.themoviedb_api_mvp.ui.details

import com.ezatpanah.themoviedb_api_mvp.db.MoviesEntity
import com.ezatpanah.themoviedb_api_mvp.response.CreditsLisResponse
import com.ezatpanah.themoviedb_api_mvp.response.DetailsMovieResponse
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface DetailsContracts {
    interface View : BaseView {
        fun loadDetailsMovie(data : DetailsMovieResponse)
        fun loadCreditsMovie(data : CreditsLisResponse)

        //db
        fun updateFavorite(isAdded: Boolean)

    }

    interface Presenter : BasePresenter {
        fun callDetailsMovie(id: Int)
        fun callCreditsMovie(id: Int)
        fun saveMovie(entity: MoviesEntity)
        fun deleteMovie(entity: MoviesEntity)
        fun checkFavorite(id: Int)

    }
}