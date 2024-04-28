package com.pankti.jetpackcomposedemo.democompose.ui.eventsListeners

import com.pankti.democompose.domain.entries.userprofile.AddUser

sealed class UserProfileEventListeners() {

    data object OnBackPressed : UserProfileEventListeners()
    class RegisterUser(var userDetail: AddUser) : UserProfileEventListeners()
    class UpdateUser(var userId: String, var userDetail: AddUser) : UserProfileEventListeners()
    class DeleteUser(var userId: String) : UserProfileEventListeners()
}