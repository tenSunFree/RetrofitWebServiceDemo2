package com.home.retrofitwebservicedemo2.posts.model.repository

import com.home.retrofitwebservicedemo2.posts.model.pojo.PostsPojo
import com.home.retrofitwebservicedemo2.common.network.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject
constructor(private val api: Api) {

    suspend fun getPhotos(): List<PostsPojo> {
        return api.getPhotos()
    }
}
