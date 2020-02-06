package com.home.retrofitwebservicedemo2.albums.model.repository

import com.home.retrofitwebservicedemo2.albums.model.pojo.AlbumsPojo
import com.home.retrofitwebservicedemo2.common.network.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRepository @Inject
constructor(private val api: Api) {

    suspend fun getAlbums(): List<AlbumsPojo> {
        return api.getAlbums()
    }
}

