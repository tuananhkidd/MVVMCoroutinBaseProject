package com.group.core_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout

abstract class CustomViewRelativeLayout : RelativeLayout {
    private val layoutRes = getLayoutRes()
    private val styableRes = getStyableRes()
    var attrs: AttributeSet? = null
    protected var view: View? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        this.attrs = attrs
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        this.attrs = attrs
        init()
    }

    abstract fun getLayoutRes(): Int
    abstract fun getStyableRes(): IntArray?
    private fun init() {
        initLayout()
    }

    private fun initLayout() {
        view = LayoutInflater.from(context).inflate(layoutRes, this, true)
        initView()
        initListener()
        initData()
        styableRes?.let {
            val mTypedArray =
                context.theme.obtainStyledAttributes(attrs, it, 0, 0)
            initDataFromStyable(mTypedArray)
        }
    }

    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()
    abstract fun initDataFromStyable(mTypedArray: TypedArray?)

}