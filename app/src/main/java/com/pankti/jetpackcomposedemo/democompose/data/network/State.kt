package com.pankti.jetpackcomposedemo.democompose.data.network

sealed class State {
    object Loading : State()
    class Success<T>(var data : T) : State()
    class Error(var error : Throwable) : State()
}