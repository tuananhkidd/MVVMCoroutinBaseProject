package com.group.core.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group.core.R
import com.group.core.extension.inflate

abstract class EndlessLoadingRecyclerViewAdapter(context: Context, enableSelectedMode: Boolean) :
        RecyclerViewAdapter(context, enableSelectedMode) {

    private var disableLoadMore = false
    protected var isLoading = false

    fun setLoadingMoreListener(onLoadMore : () -> Unit = {}) {
        this.onLoadMore = onLoadMore
        enableLoadingMore(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        var firstVisibleItemPosition = 0
                        var lastVisibleItemPosition = 0

                        if (disableLoadMore || isLoading) {
                            return
                        }
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is GridLayoutManager) {
                            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        }else if (layoutManager is LinearLayoutManager) {
                            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                            lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                        }
                        if (firstVisibleItemPosition > 0 && lastVisibleItemPosition == itemCount - 1) {
                            isLoading = true
                            onLoadMore.invoke()
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun enableLoadingMore(enable: Boolean) {
        this.disableLoadMore = !enable
    }

    fun showLoadingItem(isScroll: Boolean) {
        addModel(null, VIEW_TYPE_LOADING, isScroll)
    }

    fun hideLoadingItem() {
        if (isLoading) {
            removeModel(itemCount - 1)
            isLoading = false
        }
    }

    override fun solvedOnCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                LoadingViewHolder(parent.inflate(R.layout.layout_load_more))
            }

            else -> {
                initNormalViewHolder(parent)!!
            }
        }
    }

    override fun solvedOnBindViewHolder(viewHolder: RecyclerView.ViewHolder, viewType: Int, position: Int) {
        when (viewType) {
            VIEW_TYPE_LOADING -> {
//                bindLoadingViewHolder(viewHolder as LoadingViewHolder, position)
            }

            else -> {
                bindNormalViewHolder(viewHolder as NormalViewHolder, position)
            }
        }
    }


    private var onLoadMore : () -> Unit = {}

//    protected abstract fun initLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
//
//    protected abstract fun bindLoadingViewHolder(loadingViewHolder: LoadingViewHolder, position: Int)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val VIEW_TYPE_LOADING = -1
    }
}
