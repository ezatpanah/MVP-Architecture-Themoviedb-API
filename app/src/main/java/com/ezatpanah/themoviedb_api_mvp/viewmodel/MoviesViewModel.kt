package com.ezatpanah.themoviedb_api_mvp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ezatpanah.themoviedb_api_mvp.paging.MoviesPagingSource
import com.ezatpanah.themoviedb_api_mvp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class MoviesViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}