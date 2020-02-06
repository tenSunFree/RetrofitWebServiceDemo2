package com.home.retrofitwebservicedemo2.posts.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.home.retrofitwebservicedemo2.R
import com.home.retrofitwebservicedemo2.albums.view.activity.AlbumsActivity
import com.home.retrofitwebservicedemo2.common.base.BaseActivity
import com.home.retrofitwebservicedemo2.posts.model.viewdata.PostsViewData
import com.home.retrofitwebservicedemo2.posts.model.viewstate.HomeViewState
import com.home.retrofitwebservicedemo2.posts.model.viewstate.Loading
import com.home.retrofitwebservicedemo2.posts.model.viewstate.NetworkError
import com.home.retrofitwebservicedemo2.posts.model.viewstate.Success
import com.home.retrofitwebservicedemo2.posts.view.adapter.PostsAdapter
import com.home.retrofitwebservicedemo2.posts.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        initializeViewModel()
        initializeView()
    }

    private fun initializeViewModel() {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)
        homeViewModel.requestData()
        homeViewModel.getHomeViewState().observe(this, Observer { setViewState(it) })
    }

    private fun initializeView() {
        image_view_favorite.setOnClickListener { AlbumsActivity.start(this) }
    }

    private fun setViewState(homeViewState: HomeViewState) {
        when (homeViewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(homeViewState.message)
            }
            is Success -> {
                setProgress(false)
                showRecyclerView(homeViewState.data)
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) showProgress()
        else hideProgress()
    }

    private fun showRecyclerView(data: PostsViewData) {
        val adapter = PostsAdapter()
        adapter.updateList(data.list.toMutableList())
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
