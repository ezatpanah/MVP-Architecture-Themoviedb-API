package com.ezatpanah.themoviedb_api_mvp.ui.details

import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenter
import com.ezatpanah.themoviedb_api_mvp.ui.base.BaseView

interface DetailsContracts {
    interface View : BaseView {
        fun loadDetailsMovie()
        fun saveState()
    }

    interface Presenter : BasePresenter {
        fun callDetailsMovie()
        fun saveFavorite()
    }
}