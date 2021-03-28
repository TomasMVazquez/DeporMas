package com.applications.toms.depormas.utils

import androidx.lifecycle.Observer

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class EventWrapper<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<EventWrapper<T>> {
    override fun onChanged(eventWrapper: EventWrapper<T>?) {
        eventWrapper?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}