package com.lvb.projects.app_news_mvvm.util.state

sealed class StateResource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : StateResource<T>(data)
    class Error<T>(message: String, data: T? = null) : StateResource<T>(data, message)
    class Loading<T>(data: T? = null) : StateResource<T>(data)
}