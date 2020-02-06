package com.home.retrofitwebservicedemo2.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.retrofitwebservicedemo2.albums.viewmodel.AlbumsViewModel
import com.home.retrofitwebservicedemo2.posts.viewmodel.PostsViewModel
import com.home.retrofitwebservicedemo2.common.di.other.ViewModelFactory
import com.home.retrofitwebservicedemo2.common.di.other.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(albumsViewModel: AlbumsViewModel): ViewModel
}
