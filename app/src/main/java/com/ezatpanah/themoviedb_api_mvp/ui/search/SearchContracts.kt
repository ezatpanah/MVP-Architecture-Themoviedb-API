package com.ezatpanah.themoviedb_api_mvp.ui.search

import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface SearchContracts {
    interface View : BaseView {
        fun loadSearchMoviesList(data : CommonMoviesListResponse)
    }

    interface Presenter : BasePresenter {
        fun callSearchMoviesList(page: Int,query: String)
    }
}