package com.pankti.democompose.domain.entries.userprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pankti.democompose.domain.usecase.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(var useCase: UserProfileUseCase): ViewModel() {

    private var _userList = MutableStateFlow<List<UserProfileModel>>(listOf())
    var userList : StateFlow<List<UserProfileModel>> = _userList

    init {
        getUserList()
    }

    companion object{
        const val MALE = "male"
        const val FEMALE = "female"

        const val ACTIVE = "active"
        const val INACTIVE = "inactive"

        // Capitalize
        fun String.toCaps(): String{
          return  this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }

    private fun getUserList(){
        viewModelScope.launch {
          val data =  useCase.getUserList()
            Log.e("User List ------------>", "getUserList: "+ Gson().toJson(data) )
          _userList.value = data.sortedByDescending { it.userId }
        }
    }

    fun addUser(requestData : AddUser, onSuccess : (String) -> Unit){
        Log.e("EEE", "addUpdateUser: "+ Gson().toJson(requestData))

        viewModelScope.launch {
            val data  = useCase.addUser(requestData)
            if (data != null){
                onSuccess("User added successfully")
                getUserList()
            }
        }
    }

    fun updateUser(userId : String, requestData : AddUser, onSuccess : (String) -> Unit ){
        Log.e("Update User : ", Gson().toJson(requestData))

        viewModelScope.launch {
            val data  = useCase.updateUser(userId,requestData)
            if (data != null){
                onSuccess("User updated successfully")
                getUserList()
            }
        }
    }

    fun deleteUser(userId : String,onSuccess : (String) -> Unit){
        viewModelScope.launch {
            val data  = useCase.deleteUser(userId)
            if (data != null){
                onSuccess("User deleted successfully")
                getUserList()
            }
        }
    }
}