package com.ezatpanah.themoviedb_api_mvp.ui.details

import android.util.Log
import com.ezatpanah.themoviedb_api_mvp.repository.ApiRepository
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenterImpl
import com.ezatpanah.themoviedb_api_mvp.utils.applyIoScheduler
import javax.inject.Inject

class DetailsPresenter
@Inject constructor(
    private val repository: ApiRepository,
    val view: DetailsContracts.View,
) : DetailsContracts.Presenter, BasePresenterImpl() {
    override fun callDetailsMovie(id: Int) {
        disposable = repository
            .getMovieDetails(id)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("DetailsPresenter", "itBody : $itBody")
                            view.loadDetailsMovie(itBody)
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

    override fun callCreditsMovie(id: Int) {
        disposable = repository
            .getMovieCredits(id)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("DetailsPresenter", "itBody : $itBody")
                            view.loadCreditsMovie(itBody)
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