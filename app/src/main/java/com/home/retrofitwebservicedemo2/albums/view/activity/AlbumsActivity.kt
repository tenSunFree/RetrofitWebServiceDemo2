package com.home.retrofitwebservicedemo2.albums.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.home.retrofitwebservicedemo2.R
import com.home.retrofitwebservicedemo2.albums.model.viewdata.AlbumsViewData
import com.home.retrofitwebservicedemo2.albums.model.viewstate.AlbumsViewState
import com.home.retrofitwebservicedemo2.albums.model.viewstate.Loading
import com.home.retrofitwebservicedemo2.albums.model.viewstate.NetworkError
import com.home.retrofitwebservicedemo2.albums.model.viewstate.Success
import com.home.retrofitwebservicedemo2.albums.view.adapter.AlbumsAdapter
import com.home.retrofitwebservicedemo2.albums.viewmodel.AlbumsViewModel
import com.home.retrofitwebservicedemo2.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class AlbumsActivity : BaseActivity() {

    companion object {
        fun start(context: Activity) {
            val starter = Intent(context, AlbumsActivity::class.java)
            context.startActivity(starter)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var albumsViewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        initializeViewModel()
    }

    private fun initializeViewModel() {
        albumsViewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumsViewModel::class.java)
        albumsViewModel.requestData()
        albumsViewModel.getViewState().observe(this, Observer { setViewState(it) })
    }

    private fun setViewState(albumsViewState: AlbumsViewState) {
        when (albumsViewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(albumsViewState.message)
            }
            is Success -> {
                setProgress(false)
                showRecyclerView(albumsViewState.data)
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) showProgress()
        else hideProgress()
    }

    private fun showRecyclerView(viewData: AlbumsViewData) {
        val adapter = AlbumsAdapter()
        adapter.updateList(viewData.list.toMutableList())
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }
}