package com.ezatpanah.themoviedb_api_mvp.ui.home

import android.util.Log
import com.ezatpanah.themoviedb_api_mvp.repository.ApiRepository
import com.ezatpanah.themoviedb_api_mvp.ui.base.BasePresenterImpl
import com.ezatpanah.themoviedb_api_mvp.utils.applyIoScheduler
import javax.inject.Inject

class HomePresenter
@Inject constructor(
    private val repository: ApiRepository,
    val view: HomeContracts.View,
) : HomeContracts.Presenter, BasePresenterImpl() {

    override fun callUpcomingMoviesList(page: Int) {
        disposable = repository
            .getUpcomingMoviesList(page)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("HomePresenter", "itBody : $itBody")
                            view.loadUpcomingMoviesList(itBody)
                        }
                    in 300..399 -> {
                        Log.d("HomePresenter", " Redirection messages : ${response.code()}")
                    }
                    in 400..499 -> {
                        Log.d("HomePresenter", " Client error responses : ${response.code()}")
                    }
                    in 500..599 -> {
                        Log.d("HomePresenter", " Server error responses : ${response.code()}")
                    }

                }
            }
    }

    override fun callGenres() {
        disposable = repository
            .getGenres()
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("MainPresenter", "itBody : $itBody")
                            view.loadGenres(itBody)
                        }
                    in 300..399 -> {
                        Log.d("MainPresenter", " Redirection messages : ${response.code()}")
                    }
                    in 400..499 -> {
                        Log.d("MainPresenter", " Client error responses : ${response.code()}")
                    }
                    in 500..599 -> {
                        Log.d("MainPresenter", " Server error responses : ${response.code()}")
                    }
                }
            }
    }

    override fun callMoviesGenres(page: Int, with_genres: String) {
        disposable = repository
            .getMoviesGenres(page, with_genres)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                            Log.e("MainPresenter", "itBody : $itBody")
                            view.loadMoviesGenres(itBody)
                        }
                    in 300..399 -> {
                        Log.d("MainPresenter", " Redirection messages : ${response.code()}")
                    }
                    in 400..499 -> {
                        Log.d("MainPresenter", " Client error responses : ${response.code()}")
                    }
                    in 500..599 -> {
                        Log.d("MainPresenter", " Server error responses : ${response.code()}")
                    }

                }
            }
    }

    override fun callPopularMoviesList(page: Int) {
        disposable = repository
            .getPopularMoviesList(page)
            .applyIoScheduler()
            .subscribe { response ->
                when (response.code()) {
                    in 200..202 ->
                        response.body()?.let { itBody ->
                                Log.e("MainPresenter", "itBody : $itBody")
                                view.loadPopularMoviesList(itBody)
                        }
                    in 300..399 -> {
                        Log.d("MainPresenter", " Redirection messages : ${response.code()}")
                    }
                    in 400..499 -> {
                        Log.d("MainPresenter", " Client error responses : ${response.code()}")
                    }
                    in 500..599 -> {
                        Log.d("MainPresenter", " Server error responses : ${response.code()}")
                    }

                }
            }

    }

}