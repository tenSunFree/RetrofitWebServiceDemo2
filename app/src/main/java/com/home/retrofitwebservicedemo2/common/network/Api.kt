package com.home.retrofitwebservicedemo2.common.network

import com.home.retrofitwebservicedemo2.albums.model.pojo.AlbumsPojo
import com.home.retrofitwebservicedemo2.posts.model.pojo.PostsPojo
import retrofit2.http.GET

interface Api {

    @GET("photos")
    suspend fun getPhotos(): List<PostsPojo>

    @GET("albums")
    suspend fun getAlbums(): List<AlbumsPojo>
}
