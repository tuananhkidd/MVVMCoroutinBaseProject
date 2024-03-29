package com.group.base.ui.home

import com.group.base.network.HomeRepository
import com.group.core.base.BaseViewModel
import com.group.core.di.CoroutineScropeIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject  constructor(
    val homeRepository: HomeRepository,
    @CoroutineScropeIO val coroutineScropeIO: CoroutineScope
) : BaseViewModel() {
    var toDoData = homeRepository.getTodo

    var zipTest = homeRepository.netWorkState

    fun zipTest() {
        homeRepository.zipRequest(coroutineScropeIO)
    }
}