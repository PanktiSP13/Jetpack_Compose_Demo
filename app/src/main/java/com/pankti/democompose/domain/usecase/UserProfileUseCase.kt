package com.pankti.democompose.domain.usecase

import com.pankti.democompose.domain.entries.userprofile.AddUser
import com.pankti.democompose.domain.entries.userprofile.UserProfileModel
import com.pankti.democompose.domain.repositories.UserProfileRepository
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(var repository: UserProfileRepository) {

    suspend fun getUserList(): List<UserProfileModel> = repository.getUserList()

    suspend fun addUser(requestBody: AddUser): UserProfileModel? = repository.addUser(requestBody)

    suspend fun updateUser(userId : String ,requestBody: AddUser): UserProfileModel? = repository.updateUser(userId,requestBody)

    suspend fun deleteUser(userId : String): UserProfileModel? = repository.deleteUser(userId)
}