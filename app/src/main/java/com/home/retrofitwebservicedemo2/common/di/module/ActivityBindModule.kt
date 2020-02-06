package com.home.retrofitwebservicedemo2.common.di.module

import com.home.retrofitwebservicedemo2.albums.view.activity.AlbumsActivity
import com.home.retrofitwebservicedemo2.posts.view.activity.PostsActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindModule {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): PostsActivity

    @ContributesAndroidInjector
    abstract fun bindAlbumsActivity(): AlbumsActivity
}
