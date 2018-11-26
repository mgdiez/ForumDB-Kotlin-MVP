package com.marcgdiez.jsonplaceholder.list

import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.list.usecase.GetItemsListUseCase
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ItemsListPresenterTest {

    @Mock
    lateinit var getItemsListUseCase: GetItemsListUseCase

    lateinit var presenter: ItemsListContract.Presenter

    @Mock
    lateinit var view: ItemsListContract.View

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = ItemsListPresenter(getItemsListUseCase, Dispatchers.Unconfined)
        presenter.attachView(view)
    }

    @Test
    fun `navigate to detail when clicked an item`() {
        val fakeItem = provideFakeItem()
        presenter.onItemClick(fakeItem)
        verify(view).navigateToDetail(fakeItem)
    }


    @Test
    fun `retrieve data on view ready`() {
        presenter.onViewReady()
        runBlocking { verify(getItemsListUseCase).execute() }
    }

    @Test
    fun `show items on data retrieved`() {
        val fakeItems = ArrayList<Item>()
        fakeItems.add(provideFakeItem())
        fakeItems.add(provideFakeItem())

        given { runBlocking { getItemsListUseCase.execute() } }.willReturn(fakeItems)

        presenter.onViewReady()

        verify(view).showItems(fakeItems)
    }

    @Test
    fun `show error when network fails`() {
        given { runBlocking { getItemsListUseCase.execute() } }.willThrow(NetworkSourceException())

        presenter.onViewReady()

        verify(view).showInternetError()
        verify(view).hideProgress()
    }

    @Test
    fun `show progress on view ready`() {
        presenter.onViewReady()
        verify(view).showProgress()
    }

    private fun provideFakeItem() = Item("id", "title", "body")
}