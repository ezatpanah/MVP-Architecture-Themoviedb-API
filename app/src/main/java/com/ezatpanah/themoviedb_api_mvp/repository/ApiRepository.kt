package com.ezatpanah.themoviedb_api_mvp.repository

import com.ezatpanah.themoviedb_api_mvp.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    //Api
    fun getUpcomingMoviesList(page: Int) = apiServices.getUpcomingMoviesList(page)
    fun getGenres()=apiServices.getGenres()
    fun getMoviesGenres(page: Int ,with_genres :String)=apiServices.getMoviesGenres(page,with_genres)
    fun getPopularMoviesList(page: Int) = apiServices.getPopularMoviesList(page)
    fun getMovieDetails(id: Int) = apiServices.getMovieDetails(id)

    fun getSearchMoviesList(page: Int,query: String) = apiServices.getSearchMoviesList(page,query)
    fun getMovieCredits(id: Int) = apiServices.getMovieCredits(id)

}