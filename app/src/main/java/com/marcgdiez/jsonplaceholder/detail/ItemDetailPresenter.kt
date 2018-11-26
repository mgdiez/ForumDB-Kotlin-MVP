package com.marcgdiez.jsonplaceholder.detail

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.BasePresenter
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.detail.usecase.GetItemCommentsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ItemDetailPresenter(
    private val getItemCommentsUseCase: GetItemCommentsUseCase, private val dispatcher: CoroutineDispatcher
) : BasePresenter<ItemDetailContract.View>(), ItemDetailContract.Presenter {

    override fun onViewReady(item: Item) {
        view?.showItemData(item.id, item.title, item.body)

        view?.showProgress()

        GlobalScope.launch(context = dispatcher) {
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