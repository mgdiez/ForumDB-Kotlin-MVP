package com.marcgdiez.jsonplaceholder.detail

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.detail.usecase.GetItemCommentsUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ItemDetailPresenterTest {

    @Mock
    lateinit var getItemCommentsUseCase: GetItemCommentsUseCase

    lateinit var presenter: ItemDetailContract.Presenter

    @Mock
    lateinit var view: ItemDetailContract.View

    lateinit var fakeItem: Item

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fakeItem = provideFakeItem()

        presenter = ItemDetailPresenter(getItemCommentsUseCase, Dispatchers.Unconfined)
        presenter.attachView(view)
    }

    @Test
    fun `show provided item on view ready`() {
        presenter.onViewReady(fakeItem)

        verify(view).showItemData(fakeItem.id, fakeItem.title, fakeItem.body)
    }

    @Test
    fun `show loading comments on view ready`() {
        presenter.onViewReady(fakeItem)

        verify(view).showProgress()
    }


    @Test
    fun `show error when network fails`() {
        given { runBlocking { getItemCommentsUseCase.execute(any()) } }.willThrow(NetworkSourceException())

        presenter.onViewReady(fakeItem)

        verify(view).showInternetError()
        verify(view).hideProgress()
    }

    @Test
    fun `show comments when data retrieved`() {
        val arrayList = ArrayList<Comment>()
        arrayList.add(provideFakeComment())

        given { runBlocking { getItemCommentsUseCase.execute(any()) } }.willReturn(arrayList)

        presenter.onViewReady(fakeItem)

        verify(view).showComments(arrayList)
        verify(view).hideProgress()
    }

    @Test
    fun `clear comment area on click text not empty`() {
        presenter.onSendCommentClick("test")

        verify(view).clearCommentArea()
    }

    @Test
    fun `add comment on click text not empty`() {
        presenter.onSendCommentClick("test")

        verify(view).addComment(any())
    }

    @Test
    fun `do nothing on click text empty`() {
        presenter.onSendCommentClick("")

        verifyNoMoreInteractions(view)
    }

    private fun provideFakeComment() = Comment("id", "name", "email", "body")

    private fun provideFakeItem() = Item("id", "title", "body")
}