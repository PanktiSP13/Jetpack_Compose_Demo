package com.pankti.democompose.ui.eventsListeners

import com.pankti.democompose.domain.entries.userprofile.AddUser

sealed class UserProfileEventListeners() {

    object OnBackPressed : UserProfileEventListeners()
    class RegisterUser(var userDetail: AddUser) : UserProfileEventListeners()
    class UpdateUser(var userId: String, var userDetail: AddUser) : UserProfileEventListeners()
    class DeleteUser(var userId: String) : UserProfileEventListeners()
}