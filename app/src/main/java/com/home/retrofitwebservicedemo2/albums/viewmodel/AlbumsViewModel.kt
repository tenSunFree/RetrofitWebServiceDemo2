package com.home.retrofitwebservicedemo2.albums.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.retrofitwebservicedemo2.albums.model.repository.AlbumsRepository
import com.home.retrofitwebservicedemo2.albums.model.viewdata.AlbumsViewData
import com.home.retrofitwebservicedemo2.albums.model.viewstate.AlbumsViewState
import com.home.retrofitwebservicedemo2.albums.model.viewstate.Loading
import com.home.retrofitwebservicedemo2.albums.model.viewstate.NetworkError
import com.home.retrofitwebservicedemo2.albums.model.viewstate.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(private val repository: AlbumsRepository) :
    ViewModel() {

    private val viewStateLiveData: MutableLiveData<AlbumsViewState> = MutableLiveData()

    fun requestData() {
        viewStateLiveData.value = Loading
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception -> onError(exception) }
        viewModelScope.launch(coroutineExceptionHandler) {
            val albums = repository.getAlbums()
            viewStateLiveData.value = Success(AlbumsViewData(albums))
        }
    }

    private fun onError(throwable: Throwable) {
        viewStateLiveData.value = NetworkError(throwable.message)
    }

    fun getViewState(): LiveData<AlbumsViewState> {
        return viewStateLiveData
    }
}
