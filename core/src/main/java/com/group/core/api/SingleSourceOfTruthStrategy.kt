package com.group.core.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


fun <A> resultLiveDataApi(networkCall: suspend () -> BaseResult<A>,
                          saveCallResult: suspend (A) -> Unit) =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading())

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == BaseResult.Status.SUCCESS){
            emit(BaseResult.success(responseStatus.data))
            saveCallResult(responseStatus.data!!)
        } else if(responseStatus.status == BaseResult.Status.ERROR){
            emit(BaseResult.error(responseStatus.message!!))
//            emitSource(source)
        }
    }

fun <A> resultLiveDataDontSave(networkCall: suspend () -> BaseResult<A>) =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading())

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == BaseResult.Status.SUCCESS){
            emit(BaseResult.success(responseStatus.data))
        } else if(responseStatus.status == BaseResult.Status.ERROR){
            emit(BaseResult.error(responseStatus.message!!))
        }
    }

fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> BaseResult<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<BaseResult<A>> =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading())
        val source = databaseQuery.invoke().map { BaseResult.success(it) }
//        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == BaseResult.Status.SUCCESS) {
            emit(BaseResult.success(responseStatus.data))
            responseStatus.data?.let {
                saveCallResult(responseStatus.data!!)
            }

        } else if (responseStatus.status == BaseResult.Status.ERROR) {
            emit(BaseResult.error(responseStatus.message!!))
//            emitSource(source)
        }
    }

fun <A> resultLiveDataApiNotSaveDb(networkCall: suspend () -> BaseResult<A>) =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading())

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == BaseResult.Status.SUCCESS){
            emit(BaseResult.success(responseStatus.data))
        } else if(responseStatus.status == BaseResult.Status.ERROR){
            emit(BaseResult.error(responseStatus.message!!))
        }
    }