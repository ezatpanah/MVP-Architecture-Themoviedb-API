package com.ezatpanah.themoviedb_api_mvp.api

import com.ezatpanah.themoviedb_api_mvp.response.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    //    https://api.themoviedb.org/3/movie/upcoming?api_key=***&page=1
    //    https://api.themoviedb.org/3/movie/popular?api_key=***
    //    https://api.themoviedb.org/3/genre/movie/list?api_key=***
    //    https://api.themoviedb.org/3/search/movie?api_key=***&query=MovieName&page=1
    //    https://api.themoviedb.org/3/movie/{movie_id}?api_key=***
    //    https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=***



    @GET("movie/upcoming")
    fun getUpcomingMoviesList(@Query("page") page: Int): Single<Response<UpcomingMoviesListResponse>>

    @GET("genre/movie/list")
    fun getGenres(): Single<Response<GenresListResponse>>

    @GET("discover/movie")
    fun getMoviesGenres(@Query("page") page: Int,@Query("with_genres") with_genres: String): Single<Response<CommonMoviesListResponse>>

    @GET("movie/popular")
    fun getPopularMoviesList(@Query("page") page: Int ): Single<Response<CommonMoviesListResponse>>

    @GET("search/movie")
    fun getSearchMoviesList(@Query("page") page: Int,@Query("query") query: String): Single<Response<CommonMoviesListResponse>>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<Response<DetailsMovieResponse>>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") id: Int): Single<Response<CreditsLisResponse>>

}