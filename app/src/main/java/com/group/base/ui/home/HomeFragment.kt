package com.group.base.ui.home

import android.util.Log
import com.group.base.R
import com.group.base.network.HomeRepository
import com.group.core.base.BaseFragment
import com.group.core.extension.injectViewModel
import com.group.base.models.Test

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    override fun backFromAddFragment() {

    }

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.zipTest()
    }

    override fun initData() {
        viewModel.zipTest.observe(viewLifecycleOwner, {
            handleObjectResponse(it)
        })
    }

    override fun <U> getObjResponse(data: U) {
        if(data is HomeRepository.Demo){
            Log.v("ahuhu",data.detail.toString())
        }
    }

    override fun <U> getListResponse(data: List<U>?) {
        data?.let {
            data as List<Test>
            Log.v("ahuhu","list ${data.toString()}")
        }
    }

    override fun initListener() {
    }

    override fun backPressed(): Boolean {
        return false
    }
}