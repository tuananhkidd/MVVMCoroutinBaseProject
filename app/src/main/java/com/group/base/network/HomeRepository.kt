package com.group.base.network

import androidx.lifecycle.MutableLiveData
import com.group.core.api.BaseResult
import com.group.core.api.resultLiveDataApiNotSaveDb
import com.group.core.extension.zip
import com.group.base.models.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeDataSource: HomeDataSource) {
    var netWorkState = MutableLiveData<BaseResult<Demo>>()
    var getTodo = resultLiveDataApiNotSaveDb {
        homeDataSource.getTodo()
    }

    fun zipRequest(coroutineScope: CoroutineScope) {
        try {
            coroutineScope.launch {
                netWorkState.postValue(BaseResult.loading())
                val listTodo = async { homeDataSource.getTodo() }
                val detailTodo = async { homeDataSource.getDetailTodo() }
                val response = zip(
                    listTodo, detailTodo,
                    CoroutineStart.DEFAULT
                ) { listTodoRes, detail->
                    netWorkState.postValue(BaseResult.success(Demo(listTodoRes.data,detail.data)))
                }
            }
        }catch (e:Exception){
            netWorkState.postValue(BaseResult.error(e.message))
        }

    }

    class Demo(var listTodo:List<Test>?,var detail : Test?)
}