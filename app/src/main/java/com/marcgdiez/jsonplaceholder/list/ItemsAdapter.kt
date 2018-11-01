package com.marcgdiez.jsonplaceholder.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.marcgdiez.jsonplaceholder.R
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.extensions.inflate
import kotlinx.android.synthetic.main.item_adapter.view.*

class ItemsAdapter(val onItemClickListener: (Item) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(parent.inflate(R.layout.item_adapter))

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position]) { item -> onItemClickListener(item) }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Item, listener: (Item) -> Unit) = with(itemView) {
        title.text = item.title
        body.text = item.body.take(MAX_CHARACTERS)
        itemId.text = item.id
        setOnClickListener { listener(item) }
    }

    companion object {
        const val MAX_CHARACTERS = 80
    }
}
