package com.group.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.group.core.api.BaseResult
import com.group.core_view.LoadingDialog
import javax.inject.Inject

abstract class BaseFragment<DB:ViewDataBinding> : Fragment() {

    protected lateinit var binding : DB

    private var lastTimeClick: Long = 0

    protected var viewController : ViewController? = null

    private val loadingDialog : LoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutInflater = LayoutInflater.from(requireContext())
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
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
                hideLoadingDialog()
                getObjResponse(baseResult.data)
            }
            BaseResult.Status.ERROR -> {
                hideLoadingDialog()
            }
        }
    }

    fun<T> handleListResponse(baseResult: BaseResult<List<T>?>){
        when(baseResult.status){
            BaseResult.Status.LOADING -> { showLoadingDialog() }
            BaseResult.Status.SUCCESS -> {
                hideLoadingDialog()
                getListResponse(baseResult.data)
            }
            BaseResult.Status.ERROR -> {
                hideLoadingDialog()
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

    protected fun hideLoadingDialog(){
        loadingDialog.hide()
    }

}