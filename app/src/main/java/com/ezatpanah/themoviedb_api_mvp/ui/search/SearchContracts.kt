package com.ezatpanah.themoviedb_api_mvp.ui.search

import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface SearchContracts {
    interface View : BaseView {
        fun loadSearchMoviesList()
    }

    interface Presenter : BasePresenter {
        fun callSearchMoviesList()
    }
}