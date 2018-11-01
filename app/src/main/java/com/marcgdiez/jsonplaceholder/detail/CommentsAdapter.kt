package com.marcgdiez.jsonplaceholder.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.marcgdiez.jsonplaceholder.R
import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.extensions.inflate
import kotlinx.android.synthetic.main.comment_adapter.view.*


class CommentsAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var comments: MutableList<Comment> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent.inflate(R.layout.comment_adapter))


    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = comments.size

    fun setItems(comments: MutableList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
        notifyItemInserted(comments.lastIndex)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(comments[position])
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(comment: Comment) = with(itemView) {
        name.text = comment.name
        email.text = comment.email
        commentBody.text = comment.body
    }
}
