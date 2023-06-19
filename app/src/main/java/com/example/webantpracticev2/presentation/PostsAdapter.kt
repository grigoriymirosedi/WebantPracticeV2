package com.example.webantpracticev2.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.webantpracticev2.R
import com.example.webantpracticev2.data.remote.dto.PostListItem

class PostsAdapter: RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val differCallback = object: DiffUtil.ItemCallback<PostListItem>() {
        override fun areItemsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int) {
        val postItem = differ.currentList[position]
        viewHolder.titleText.text = postItem.title
        viewHolder.bodyText.text = postItem.body
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var titleText = view.findViewById<TextView>(R.id.tv_title_text)
        var bodyText = view.findViewById<TextView>(R.id.tv_body_text)
    }

}