package com.marcgdiez.jsonplaceholder.detail

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.MvpPresenter
import com.marcgdiez.jsonplaceholder.core.MvpView

interface ItemDetailContract {

    interface View : MvpView {
        fun hideProgress()
        fun showProgress()
        fun showComments(comments: List<Comment>)
        fun showItemData(id: String, itemTitle: String, bodyItem: String)
        fun clearCommentArea()
        fun addComment(comment: Comment)
        fun showInternetError()

    }

    interface Presenter : MvpPresenter<View> {
        fun onViewReady(item: Item)
        fun onSendCommentClick(text: String)
    }
}