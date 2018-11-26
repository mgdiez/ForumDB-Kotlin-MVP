package com.marcgdiez.jsonplaceholder.list

import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.BasePresenter
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.list.usecase.GetItemsListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemsListPresenter(
    private val getItemsListUseCase: GetItemsListUseCase,
    private val dispatcher: CoroutineDispatcher
) : BasePresenter<ItemsListContract.View>(),
    ItemsListContract.Presenter {

    override fun onViewReady() {
        view?.showProgress()

        GlobalScope.launch(context = dispatcher) {
            try {
                val items = getItemsListUseCase.execute()
                view?.showItems(items)
            } catch (e: NetworkSourceException) {
                view?.showInternetError()
            } finally {
                view?.hideProgress()
            }
        }

    }

    override fun onItemClick(it: Item) {
        view?.navigateToDetail(it)
    }
}