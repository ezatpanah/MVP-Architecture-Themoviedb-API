package com.ezatpanah.themoviedb_api_mvp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page.Companion.COUNT_UNDEFINED
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.ezatpanah.themoviedb_api_mvp.adapter.CommonMoviesAdapter
import com.ezatpanah.themoviedb_api_mvp.repository.ApiRepository
import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val repository: ApiRepository,
) : RxPagingSource<Int, CommonMoviesListResponse.Result>() {


    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CommonMoviesListResponse.Result>> {

        var nextPageNumber = params.key ?: 1

        return repository.getPopularMoviesList( nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map { response -> response.body()?.results.let { toLoadResult(it!!, nextPageNumber) } }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(
        response: MutableList<CommonMoviesListResponse.Result>,
        position:Int
    ): LoadResult<Int, CommonMoviesListResponse.Result> {

        return LoadResult.Page(
            response,
            null,
            position + 1,
            COUNT_UNDEFINED,
            COUNT_UNDEFINED
        )

    }

    override fun getRefreshKey(state: PagingState<Int, CommonMoviesListResponse.Result>): Int? {
        return null
    }
}