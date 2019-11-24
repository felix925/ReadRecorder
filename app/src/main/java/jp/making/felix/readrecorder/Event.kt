package jp.making.felix.readrecorder

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T){
    var alreadyHandled = false
        private set

    fun getContentIfNotHandled():T?{
        return if(alreadyHandled){
            null
        } else{
            alreadyHandled = true
            content
        }
    }
    fun peekContent():T = content
}

/**
 * イベントのオブザーバー
 * イベントがすでにハンドルされていなければ [onEventUnhandledContent]が呼ばれる。
 */
class EventObserber<T>(private val onEventUnhandledContent: (T) -> Unit):Observer<Event<T>>{
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let{
            onEventUnhandledContent(it)
        }
    }
}