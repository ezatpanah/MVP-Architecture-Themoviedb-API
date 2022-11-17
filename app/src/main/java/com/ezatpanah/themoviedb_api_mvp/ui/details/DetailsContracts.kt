package com.ezatpanah.themoviedb_api_mvp.ui.details

import com.ezatpanah.themoviedb_api_mvp.response.CreditsLisResponse
import com.ezatpanah.themoviedb_api_mvp.response.DetailsMovieResponse
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface DetailsContracts {
    interface View : BaseView {
        fun loadDetailsMovie(data : DetailsMovieResponse)
        fun loadCreditsMovie(data : CreditsLisResponse)
    }

    interface Presenter : BasePresenter {
        fun callDetailsMovie(id: Int)
        fun callCreditsMovie(id: Int)
    }
}