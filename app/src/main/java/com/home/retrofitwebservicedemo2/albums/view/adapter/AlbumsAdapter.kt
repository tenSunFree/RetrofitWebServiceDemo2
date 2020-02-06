package com.home.retrofitwebservicedemo2.albums.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.home.retrofitwebservicedemo2.albums.model.pojo.AlbumsPojo
import com.home.retrofitwebservicedemo2.databinding.ActivityAlbumsRecyclerViewItemBinding

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private var albumsPojoMutableList: MutableList<AlbumsPojo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityAlbumsRecyclerViewItemBinding
            .inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val albumsPojo = albumsPojoMutableList[position]
        configureTitle(holder.binding.textViewTitle, albumsPojo)
    }

    private fun configureTitle(textView: TextView, pojo: AlbumsPojo) {
        textView.text = pojo.title
    }

    override fun getItemCount(): Int {
        return albumsPojoMutableList.size
    }

    fun updateList(list: MutableList<AlbumsPojo>) {
        albumsPojoMutableList = list
    }

    class ViewHolder(val binding: ActivityAlbumsRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
