package com.ezatpanah.themoviedb_api_mvp.di

import androidx.fragment.app.Fragment
import com.ezatpanah.themoviedb_api_mvp.ui.details.DetailsContracts
import com.ezatpanah.themoviedb_api_mvp.ui.favorites.FavoritesContracts
import com.ezatpanah.themoviedb_api_mvp.ui.home.HomeContracts
import com.ezatpanah.themoviedb_api_mvp.ui.search.SearchContracts
import com.ezatpanah.themoviedb_api_mvp.ui.search.SearchFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class ContractsModule {

    @Provides
    fun homeView(fragment: Fragment): HomeContracts.View {
        return fragment as HomeContracts.View
    }

    @Provides
    fun detailView(fragment: Fragment): DetailsContracts.View {
        return fragment as DetailsContracts.View
    }

    @Provides
    fun searchView(fragment: Fragment): SearchContracts.View {
        return fragment as SearchContracts.View
    }

    @Provides
    fun favoriteView(fragment: Fragment): FavoritesContracts.View {
        return fragment as FavoritesContracts.View
    }

}