package com.ezatpanah.themoviedb_api_mvp.ui.search

import android.util.Log
import com.ezatpanah.themoviedb_api_mvp.repository.ApiRepository
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenterImpl
import com.ezatpanah.themoviedb_api_mvp.ui.details.DetailsContracts
import com.ezatpanah.themoviedb_api_mvp.utils.applyIoScheduler
import javax.inject.Inject

class SearchPresenter
@Inject constructor(
    private val repository: ApiRepository,
    val view: SearchContracts.View,
) : SearchContracts.Presenter, BasePresenterImpl() {
    override fun callSearchMoviesList(page: Int,query: String) {
        disposable = repository
            .getSearchMoviesList(1, query)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("DetailsPresenter", "itBody : $itBody")
                            view.loadSearchMoviesList(itBody)
                        }
                    in 300..399 -> {
                        Log.d("DetailsPresenter", " Redirection messages : ${response.code()}")
                    }
                    in 400..499 -> {
                        Log.d("DetailsPresenter", " Client error responses : ${response.code()}")
                    }
                    in 500..599 -> {
                        Log.d("DetailsPresenter", " Server error responses : ${response.code()}")
                    }
                }
            }
    }

}