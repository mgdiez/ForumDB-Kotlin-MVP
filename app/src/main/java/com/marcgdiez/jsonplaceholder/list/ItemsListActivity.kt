package com.marcgdiez.jsonplaceholder.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.marcgdiez.jsonplaceholder.R
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.detail.ItemDetailActivity
import com.marcgdiez.jsonplaceholder.extensions.internetErrorDialog
import com.marcgdiez.jsonplaceholder.extensions.show
import kotlinx.android.synthetic.main.activity_list_items.*
import org.koin.android.ext.android.inject

class ItemsListActivity : AppCompatActivity(), ItemsListContract.View {

    private val presenter: ItemsListContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)
        with(recyclerView) {
            adapter = ItemsAdapter { presenter.onItemClick(it) }
            layoutManager = LinearLayoutManager(this@ItemsListActivity)
        }

        presenter.attachView(this)
        presenter.onViewReady()
    }

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showItems(items: List<Item>) {
        recyclerView.show()
        val transactionsListAdapter = recyclerView.adapter as? ItemsAdapter
        transactionsListAdapter?.setItems(items)
    }

    override fun navigateToDetail(it: Item) =
        startActivity(ItemDetailActivity.getCallingIntent(this, it))

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showInternetError() {
        internetErrorDialog()
    }
}
