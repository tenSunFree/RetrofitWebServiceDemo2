package com.home.retrofitwebservicedemo2.albums.model.viewstate

import com.home.retrofitwebservicedemo2.albums.model.viewdata.AlbumsViewData

sealed class AlbumsViewState

object Loading : AlbumsViewState()
data class Success(val data: AlbumsViewData) : AlbumsViewState()
data class NetworkError(val message: String?) : AlbumsViewState()