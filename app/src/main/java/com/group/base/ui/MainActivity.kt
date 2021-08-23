package com.group.base.ui

import com.group.base.R
import com.group.base.ui.home.HomeFragment
import com.group.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.id.frame_main

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initView() {
        getViewController().addFragment(HomeFragment::class.java,null,false)
    }

    override fun initData() {
        
    }

    override fun initListener() {
        
    }
}