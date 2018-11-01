package com.marcgdiez.jsonplaceholder.list

import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.BasePresenter
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.list.usecase.GetItemsListUseCase
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

class ItemsListPresenter(val getItemsListUseCase: GetItemsListUseCase) : BasePresenter<ItemsListContract.View>(),
    ItemsListContract.Presenter {

    override fun onViewReady() {
        view?.showProgress()

        GlobalScope.launch(context = Dispatchers.Main) {
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