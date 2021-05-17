package com.group.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.group.core.define.Constants
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    private var viewController : ViewController? = null
    private var lastTimeClick: Long = 0

    fun getViewController() : ViewController {
        if(viewController == null){
            viewController = ViewController(layoutId,supportFragmentManager)

        }
        return viewController!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        viewController = ViewController(layoutId,supportFragmentManager)
        initView()
        initData()
        initListener()
    }

    //click able
    val isDoubleClick: Boolean
        get() {
            val timeNow = System.currentTimeMillis()
            if (timeNow - lastTimeClick >= Constants.DURATION_TIME_CLICKABLE) {
                //click able
                lastTimeClick = timeNow
                return false
            }
            return true
        }

    override fun onBackPressed() {
        if(viewController!= null && viewController?.currentFragment!=null){
            viewController?.currentFragment?.backPressed()
            super.onBackPressed()
        }else{
            super.onBackPressed()
        }

    }

    protected abstract val layoutResId :  Int
    protected abstract val layoutId :  Int
    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initListener()

}