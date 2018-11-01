package com.marcgdiez.jsonplaceholder.core

open class BasePresenter<T : MvpView> : MvpPresenter<T> {

    var view: T? = null
        private set

    override val isViewAttached: Boolean
        get() = view != null

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }
}