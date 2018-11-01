package com.marcgdiez.jsonplaceholder.business

import android.os.Parcel
import com.marcgdiez.jsonplaceholder.core.KParcelable
import com.marcgdiez.jsonplaceholder.core.parcelableCreator

data class Item(val id: String, val title: String, val body: String) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(
            ::Item
        )
    }

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(id)
            writeString(title)
            writeString(body)
        }
    }
}