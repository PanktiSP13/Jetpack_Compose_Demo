package com.pankti.jetpackcomposedemo.democompose.ui.eventsListeners

sealed class NetworkCallWithKtorEventListener() {

    data object  OnBackPressed : NetworkCallWithKtorEventListener()
}