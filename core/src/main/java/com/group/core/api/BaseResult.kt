package com.group.core.api

data class BaseResult<out T> (val status: Status, val data: T?, val message: String?, val code: Int =0){
    enum class Status{
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{
        fun <T> success(data: T?): BaseResult<T> {
            return BaseResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String? = null, data: T? = null, code: Int = 0): BaseResult<T> {
            return BaseResult(Status.ERROR, data, message, code)
        }

        fun <T> loading(data: T? =null) : BaseResult<T> {
            return BaseResult(Status.LOADING, data, null)
        }
    }
}