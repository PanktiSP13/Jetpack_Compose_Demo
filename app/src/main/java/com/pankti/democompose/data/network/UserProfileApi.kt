package com.pankti.democompose.data.network

import android.util.Log
import com.pankti.democompose.data.network.NetworkCallRoutes.ADD_USER
import com.pankti.democompose.data.network.NetworkCallRoutes.DELETE_USER
import com.pankti.democompose.data.network.NetworkCallRoutes.UPDATE_USER
import com.pankti.democompose.domain.entries.userprofile.AddUser
import com.pankti.democompose.domain.entries.userprofile.UserProfileModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class UserProfileApi(var httpClient: HttpClient) {

     private val accessToken = "Bearer e1b8e7522596512841d4a1141898fbb349eb6d69eb45cc0212638dca21860369"

     suspend fun getUserList() : List<UserProfileModel>{
       return  httpClient.get(urlString = NetworkCallRoutes.USER_LIST){
           header("Authorization",accessToken)
       }.body()
     }

     suspend fun addUser(requestBody : AddUser) : UserProfileModel? {
         return try {
             val response =  httpClient.post {
                 url(urlString = ADD_USER)
                 setBody(requestBody)
                 contentType(ContentType.Application.Json)
                 header("Authorization",accessToken)
             }
             response.body()

         } catch (e:java.lang.Exception) {
             Log.e("Add User Error : ",e.message.toString())
             UserProfileModel()
         }
     }

    suspend fun updateUser(userId : Int , requestBody : AddUser) :  UserProfileModel? {
        return try {
            val response = httpClient.put {
                url(urlString = UPDATE_USER + userId)
                setBody(requestBody)
                contentType(ContentType.Application.Json)
                header("Authorization",accessToken)
            }
            response.body()

        } catch (e:Exception) {
            Log.e("Update User Error : ",e.message.toString())
            UserProfileModel()
        }
    }

    suspend fun deleteUser(userId : Int):UserProfileModel?{
        return try {
            val response = httpClient.delete {
                url(urlString = DELETE_USER + userId)
                contentType(ContentType.Application.Json)
                header("Authorization",accessToken)
            }
            response.body()

        } catch (e:Exception) {
            Log.e("Delete User Error : ",e.message.toString())
            UserProfileModel()
        }
    }
}

