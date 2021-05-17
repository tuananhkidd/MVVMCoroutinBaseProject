package com.group.core_view

import android.content.Context
import android.widget.TextView


class LoadingDialog(context: Context) : BaseCustomDialog(context) {

    private var mTvLoading: TextView? = null

    override val layoutId: Int
        get() = R.layout.dialog_loading

    override fun initViews() {
        setCancelable(false)
    }

    override fun initData() {

    }

    override fun initListeners() {
    }
}