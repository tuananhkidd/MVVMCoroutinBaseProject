package com.group.core_view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog


abstract class BaseCustomDialog(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(layoutId)
        initViews()
        initData()
        initListeners()
    }

    protected abstract val layoutId: Int
    protected abstract fun initViews()
    protected abstract fun initData()
    protected abstract fun initListeners()
}