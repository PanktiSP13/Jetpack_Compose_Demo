package com.pankti.democompose.domain.repositories

import com.pankti.democompose.domain.entries.userprofile.AddUser
import com.pankti.democompose.domain.entries.userprofile.UserProfileModel

interface UserProfileRepository {

    suspend fun getUserList() : List<UserProfileModel>
    suspend fun addUser(request : AddUser) : UserProfileModel?
    suspend fun updateUser(userId : String , request : AddUser) : UserProfileModel?
    suspend fun deleteUser(userId : String) : UserProfileModel?
}