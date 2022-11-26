package com.ezatpanah.themoviedb_api_mvp.ui.favorites

import android.util.Log
import com.ezatpanah.themoviedb_api_mvp.repository.DatabaseRepository
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenterImpl
import com.ezatpanah.themoviedb_api_mvp.ui.search.SearchContracts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class FavoritesPresenter
@Inject constructor(
    private val repository: DatabaseRepository,
    val view: FavoritesContracts.View,
) : FavoritesContracts.Presenter, BasePresenterImpl() {

    companion object{
        const val TAG="FavoritesPresenter"
    }

    override fun callFavoritesList() {
        disposable = repository
            .getAllFavoriteList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    view.loadFavoriteMovieList(it)
                } else {
                    view.showEmpty()
                }
            }
    }

}