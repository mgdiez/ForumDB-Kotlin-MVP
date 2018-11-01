package com.marcgdiez.jsonplaceholder.detail

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.BasePresenter
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.detail.usecase.GetItemCommentsUseCase
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import java.util.*

class ItemDetailPresenter(private val getItemCommentsUseCase: GetItemCommentsUseCase) :
    BasePresenter<ItemDetailContract.View>(), ItemDetailContract.Presenter {

    override fun onViewReady(item: Item) {
        view?.showItemData(item.id, item.title, item.body)

        view?.showProgress()

        GlobalScope.launch(context = Dispatchers.Main) {
            try {
                val comments = getItemCommentsUseCase.execute(item.id)
                view?.showComments(comments)
            } catch (e: NetworkSourceException) {
                view?.showInternetError()
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun onSendCommentClick(text: String) {
        when (text.isEmpty()) {
            false -> {
                view?.clearCommentArea()
                view?.addComment(Comment(Random().nextInt().toString(), "You", "youremail@gmail.com", text))
            }
        }
    }
}