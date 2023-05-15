package com.pankti.democompose.data.reposityimpl

import com.pankti.jetpackcomposedemo.democompose.data.network.UserProfileApi
import com.pankti.democompose.domain.entries.userprofile.AddUser
import com.pankti.democompose.domain.entries.userprofile.UserProfileModel
import com.pankti.democompose.domain.repositories.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(var api : UserProfileApi) : UserProfileRepository {
    override suspend fun getUserList(): List<UserProfileModel> {
        return api.getUserList()
    }

    override suspend fun addUser(request: AddUser): UserProfileModel? {
        return api.addUser(requestBody = request)
    }

    override suspend fun updateUser(userId: String, request: AddUser): UserProfileModel? {
        return api.updateUser(userId = userId.toInt(), requestBody = request)
    }

    override suspend fun deleteUser(userId: String): UserProfileModel? {
        return api.deleteUser(userId.toInt())
    }

}