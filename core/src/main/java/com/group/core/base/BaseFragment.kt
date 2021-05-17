package com.group.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.group.core.api.BaseResult
import com.group.core_view.LoadingDialog
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    private var lastTimeClick: Long = 0

    protected var viewController : ViewController? = null

    private val loadingDialog : LoadingDialog by lazy { LoadingDialog(context!!) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewController =  (activity as BaseActivity).getViewController()
        return LayoutInflater.from(context).inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    abstract fun backFromAddFragment()

    @get: LayoutRes
    protected abstract val layoutId :  Int
    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initListener()
    abstract fun backPressed() : Boolean

    fun setVC(viewController: ViewController) {
        this.viewController = viewController
    }

    fun getVC() : ViewController{
        if(viewController == null){
            viewController =  (activity as BaseActivity).getViewController()
        }

        return viewController!!
    }

    fun<T> handleObjectResponse(baseResult: BaseResult<T>){
        when(baseResult.status){
            BaseResult.Status.LOADING -> { showLoadingDialog() }
            BaseResult.Status.SUCCESS -> {
                hideLoadingDilog()
                getObjResponse(baseResult.data)
            }
            BaseResult.Status.ERROR -> {
                hideLoadingDilog()
            }
        }
    }

    fun<T> handleListResponse(baseResult: BaseResult<List<T>?>){
        when(baseResult.status){
            BaseResult.Status.LOADING -> { showLoadingDialog() }
            BaseResult.Status.SUCCESS -> {
                hideLoadingDilog()
                getListResponse(baseResult.data)
            }
            BaseResult.Status.ERROR -> {
                hideLoadingDilog()
            }
        }
    }

    open fun <U> getObjResponse(data : U){

    }

    open fun <U> getListResponse(data: List<U>?){

    }

    protected fun showLoadingDialog(){
        loadingDialog.show()
    }

    protected fun hideLoadingDilog(){
        loadingDialog.hide()
    }

}