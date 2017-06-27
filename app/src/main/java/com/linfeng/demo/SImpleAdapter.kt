package com.linfeng.demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by baozi on 2017/6/14.
 */
class SimpleAdapter<T>(datas: MutableList<T>, var layout: Int) : RecyclerView.Adapter<ViewHolder>() {
    var mDatas: MutableList<T>? = datas

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflate = from(parent?.context).inflate(layout, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return if (mDatas == null) 0 else mDatas?.size!!
    }


}