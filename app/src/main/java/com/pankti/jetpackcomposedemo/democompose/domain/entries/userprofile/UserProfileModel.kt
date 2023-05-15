package com.pankti.democompose.domain.entries.userprofile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileModel(
    @SerialName("id") var userId: Int = 0,
    @SerialName("name") var userName: String? = "",
    @SerialName("email") var userEmail: String? = "",
    @SerialName("gender") var userGender: String = "",
    var status: String? = "") {}



@Serializable
data class AddUser(
    @SerialName("id") var userId: String = "",
    @SerialName("name") var userName: String,
    @SerialName("email") var userEmail: String,
    @SerialName("gender") var userGender: String,
    @SerialName("status") var status: String = "active") {}