package com.home.retrofitwebservicedemo2.posts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.home.retrofitwebservicedemo2.R
import com.home.retrofitwebservicedemo2.databinding.ActivityPostsRecyclerViewItemBinding
import com.home.retrofitwebservicedemo2.posts.model.pojo.PostsPojo

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var postsPojoMutableList: MutableList<PostsPojo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityPostsRecyclerViewItemBinding
            .inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoritePhotoBean = postsPojoMutableList[position]
        configureTitle(holder.binding.textViewTitle, favoritePhotoBean)
        configureImage(holder.binding.imageViewThumbnail, favoritePhotoBean)
    }

    private fun configureTitle(textView: TextView, bean: PostsPojo) {
        textView.text = bean.title
    }

    private fun configureImage(showImage: ImageView, bean: PostsPojo) {
        showImage.load(bean.url) {
            crossfade(true)
            placeholder(R.color.color_561E18)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return postsPojoMutableList.size
    }

    fun updateList(list: MutableList<PostsPojo>) {
        postsPojoMutableList = list
    }

    class ViewHolder(val binding: ActivityPostsRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
