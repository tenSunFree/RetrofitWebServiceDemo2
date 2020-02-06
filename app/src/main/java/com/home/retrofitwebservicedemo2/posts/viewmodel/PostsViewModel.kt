package com.home.retrofitwebservicedemo2.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.retrofitwebservicedemo2.posts.model.repository.PostsRepository
import com.home.retrofitwebservicedemo2.posts.model.viewdata.PostsViewData
import com.home.retrofitwebservicedemo2.posts.model.viewstate.HomeViewState
import com.home.retrofitwebservicedemo2.posts.model.viewstate.Loading
import com.home.retrofitwebservicedemo2.posts.model.viewstate.NetworkError
import com.home.retrofitwebservicedemo2.posts.model.viewstate.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val repository: PostsRepository) :
    ViewModel() {

    private val viewStateLiveData: MutableLiveData<HomeViewState> = MutableLiveData()

    fun requestData() {
        viewStateLiveData.value = Loading
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception -> onError(exception) }
        viewModelScope.launch(coroutineExceptionHandler) {
            val photos = repository.getPhotos()
            viewStateLiveData.value = Success(PostsViewData(photos))
        }
    }

    private fun onError(throwable: Throwable) {
        viewStateLiveData.value = NetworkError(throwable.message)
    }

    fun getHomeViewState(): LiveData<HomeViewState> {
        return viewStateLiveData
    }
}
