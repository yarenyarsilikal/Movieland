package com.yarenyarsilikal.movieland.util


/**
 * Created by yarenyarsilikal on 13.02.2022.
 */
open class Event<out T>(private val content: T? = null) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandledOrReturnNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}