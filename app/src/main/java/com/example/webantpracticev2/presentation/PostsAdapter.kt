package com.example.webantpracticev2.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webantpracticev2.R
import com.example.webantpracticev2.data.remote.dto.PostListItem

class PostsAdapter(private var responseList: List<PostListItem>): RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int) {
        val postItem = responseList[position]
        viewHolder.titleText.text = postItem.title
        viewHolder.bodyText.text = postItem.body
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var titleText = view.findViewById<TextView>(R.id.tv_title_text)
        var bodyText = view.findViewById<TextView>(R.id.tv_body_text)
    }

}