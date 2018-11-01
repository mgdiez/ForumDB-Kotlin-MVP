package com.marcgdiez.jsonplaceholder.list

import com.marcgdiez.jsonplaceholder.core.MvpPresenter
import com.marcgdiez.jsonplaceholder.core.MvpView
import com.marcgdiez.jsonplaceholder.business.Item

interface ItemsListContract {
    interface View : MvpView {
        fun showProgress()
        fun hideProgress()
        fun showItems(items: List<Item>)
        fun navigateToDetail(it: Item)
        fun showInternetError()
    }

    interface Presenter : MvpPresenter<View> {
        fun onViewReady()
        fun onItemClick(it: Item)
    }
}