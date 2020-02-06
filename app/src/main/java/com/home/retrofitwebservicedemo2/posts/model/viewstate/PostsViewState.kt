package com.home.retrofitwebservicedemo2.posts.model.viewstate

import com.home.retrofitwebservicedemo2.posts.model.viewdata.PostsViewData

sealed class HomeViewState

object Loading : HomeViewState()
data class Success(val data: PostsViewData) : HomeViewState()
data class NetworkError(val message: String?) : HomeViewState()