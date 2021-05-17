package com.group.core.event

//import kotlinx.coroutines.experimental.channels.BroadcastChannel
//import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
//import kotlinx.coroutines.experimental.channels.ReceiveChannel
//import kotlinx.coroutines.experimental.channels.filter
//import kotlinx.coroutines.experimental.channels.map
//import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.filter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
@ExperimentalCoroutinesApi
class EventBus @Inject constructor() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)




//    inline fun <reified T> asChannel(): ReceiveChannel<T> {
//        return bus.openSubscription().filter {  }
//    }

    private val events = MutableSharedFlow<Any>()

    suspend fun dispatch(event: Any) {
        events.emit(event)
    }

    @InternalCoroutinesApi
    suspend fun on(handler: suspend (Any) -> Unit) =
        scope.launch(start = CoroutineStart.UNDISPATCHED) {

        }
}