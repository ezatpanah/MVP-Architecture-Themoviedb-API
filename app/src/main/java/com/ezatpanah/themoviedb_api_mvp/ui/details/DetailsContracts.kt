package com.ezatpanah.themoviedb_api_mvp.ui.details

import com.ezatpanah.themoviedb_api_mvp.response.DetailsMovieResponse
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface DetailsContracts {
    interface View : BaseView {
        fun loadDetailsMovie(data : DetailsMovieResponse)
    }

    interface Presenter : BasePresenter {
        fun callDetailsMovie(id: Int)
    }
}