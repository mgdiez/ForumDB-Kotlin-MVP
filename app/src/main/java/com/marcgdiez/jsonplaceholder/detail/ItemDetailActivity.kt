package com.marcgdiez.jsonplaceholder.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.marcgdiez.jsonplaceholder.R
import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.extensions.internetErrorDialog
import kotlinx.android.synthetic.main.activity_item_details.*
import org.koin.android.ext.android.inject


class ItemDetailActivity : AppCompatActivity(), ItemDetailContract.View {

    private val presenter: ItemDetailContract.Presenter by inject()

    companion object {
        fun getCallingIntent(context: Context, item: Item): Intent {
            val intent = Intent(context, ItemDetailActivity::class.java)
            intent.putExtra(ARG_ITEM, item)
            return intent
        }

        const val ARG_ITEM = "ARG_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        initToolbar()
        initView()

        presenter.attachView(this)
        presenter.onViewReady(getItem())
    }

    private fun initView() {
        val commentsListAdapter = CommentsAdapter()
        with(recyclerView) {
            adapter = commentsListAdapter
            layoutManager = LinearLayoutManager(this@ItemDetailActivity)
        }
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)

        sendComment.setOnClickListener { presenter.onSendCommentClick(commentText.text.toString()) }
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun getItem(): Item = intent.getParcelableExtra(ARG_ITEM)

    override fun hideProgress() = progressView.hide()

    override fun showProgress() = progressView.show()

    override fun showComments(comments: List<Comment>) {
        val adapter = recyclerView.adapter as? CommentsAdapter
        adapter?.setItems(comments.toMutableList())
    }

    override fun showItemData(id: String, itemTitle: String, bodyItem: String) {
        item.text = id
        titleItem.text = itemTitle
        body.text = bodyItem
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun clearCommentArea() = commentText.text.clear()

    override fun addComment(comment: Comment) {
        val adapter = recyclerView.adapter as? CommentsAdapter
        adapter?.addComment(comment)
        nestedScrollView.fullScroll(View.FOCUS_DOWN)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showInternetError() {
        internetErrorDialog()
    }
}