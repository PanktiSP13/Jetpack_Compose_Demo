package com.pankti.democompose.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pankti.democompose.R
import com.pankti.democompose.domain.entries.userprofile.AddUser
import com.pankti.democompose.domain.entries.userprofile.UserProfileModel
import com.pankti.democompose.domain.entries.userprofile.UserProfileViewModel
import com.pankti.democompose.domain.entries.userprofile.UserProfileViewModel.Companion.toCaps
import com.pankti.democompose.ui.common.AppBar
import com.pankti.democompose.ui.eventsListeners.UserProfileEventListeners
import java.util.*

@Composable
fun UserProfileRootUI(navController: NavController) {

    val viewModel = hiltViewModel<UserProfileViewModel>()
    val userList = viewModel.userList.collectAsState().value

    val context = LocalContext.current

    UserProfileUI(userList) { _event ->

        when (_event) {

            UserProfileEventListeners.OnBackPressed -> navController.popBackStack()

            is UserProfileEventListeners.RegisterUser -> viewModel.addUser(_event.userDetail, onSuccess = { _message ->
                Toast.makeText(context, _message, Toast.LENGTH_LONG).show()
            })

            is UserProfileEventListeners.UpdateUser -> viewModel.updateUser(_event.userId, _event.userDetail, onSuccess = { _message ->
                Toast.makeText(context, _message, Toast.LENGTH_LONG).show()
            })

            is UserProfileEventListeners.DeleteUser -> viewModel.deleteUser(_event.userId, onSuccess = { _message ->
                Toast.makeText(context, _message, Toast.LENGTH_LONG).show()
            })

            else -> {
                //do nothing
            }
        }
    }
}

@Composable
fun UserProfileUI(userList: List<UserProfileModel>, uiEventListener: (UserProfileEventListeners) -> Unit = {}) {

    val userDetail = remember { mutableStateOf(UserProfileModel()) }
    val isUpdateUserDetail = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        AppBar(stringResource(R.string.user_profile), true) {
            uiEventListener(UserProfileEventListeners.OnBackPressed)
        }
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {

            AddUpdateUserUI(
                isUpdateUserDetail = isUpdateUserDetail.value,
                userDetail = userDetail.value,
                onUpdatedDataClear = {
                    isUpdateUserDetail.value = false
                },
                onAddUser = { _userDetail ->
                    uiEventListener(UserProfileEventListeners.RegisterUser(_userDetail))
                },
                onUpdateUser = { _userId, _userUpdatedDetail ->
                    uiEventListener(UserProfileEventListeners.UpdateUser(_userId, _userUpdatedDetail))
                    isUpdateUserDetail.value = false
                },
                           )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            UserListUI(userList, onDeleteUser = { _userId ->
                uiEventListener(UserProfileEventListeners.DeleteUser(_userId))
            }, onItemClick = { _userDetail ->
                isUpdateUserDetail.value = true
                userDetail.value = _userDetail
            })
        }
    })
}

@Composable
fun AddUpdateUserUI(
    isUpdateUserDetail: Boolean, userDetail: UserProfileModel = UserProfileModel(), onAddUser: (AddUser) -> Unit,
    onUpdateUser: (String, AddUser) -> Unit, onUpdatedDataClear: () -> Unit) {

    val userNameTxt = rememberSaveable { mutableStateOf("") }
    val userEmailTxt = rememberSaveable { mutableStateOf("") }
    val radioOptions = listOf("Male", "Female")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val userGenderTxt = rememberSaveable { mutableStateOf("Male") }
    val isEditing = remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (isUpdateUserDetail && !isEditing.value) {
        userNameTxt.value = userDetail.userName.toString()
        userEmailTxt.value = userDetail.userEmail.toString()
        userGenderTxt.value = userDetail.userGender.toCaps()
        onOptionSelected(userGenderTxt.value)
    } else {
        // after edit , field should be clear
        if (!isEditing.value) {
            userNameTxt.value = ""
            userEmailTxt.value = ""
            userGenderTxt.value = radioOptions[0]
            onOptionSelected(userGenderTxt.value)
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {

        TextField(value = userNameTxt.value, onValueChange = { _newValue ->
            isEditing.value = true
            userNameTxt.value = _newValue
        }, label = { Text(text = "User Name") }, modifier = Modifier
            .fillMaxWidth()
            .background(Color.White))

        TextField(value = userEmailTxt.value, onValueChange = { _newValue ->
            isEditing.value = true
            userEmailTxt.value = _newValue
        }, label = { Text(text = "Email") }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .background(Color.White))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)) {
            radioOptions.forEach { text ->
                Row(modifier = Modifier
                    .selectable(selected = (text == selectedOption), onClick = {
                        isEditing.value = true
                        onOptionSelected(text)
                        userGenderTxt.value = text
                        Log.e("SDSDSDSDSDS ---------> ", "AddUpdateUserUI: $text")
                    })
                    .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(selected = (text == selectedOption), onClick = {
                        isEditing.value = true
                        onOptionSelected(text)
                        userGenderTxt.value = text
                        Log.e("RRRRRRRR ---------> ", "AddUpdateUserUI: $text")
                    })

                    Text(text = text, style = MaterialTheme.typography.body1.merge(), modifier = Modifier.padding(start = 16.dp))
                }
            }
        }

        if (isUpdateUserDetail) {

            Row(modifier = Modifier.fillMaxWidth()) {

                Button(modifier = Modifier.fillMaxWidth(0.5f), onClick = {
                    onUpdatedDataClear()
                    userNameTxt.value = ""
                    userEmailTxt.value = ""
                    userGenderTxt.value = radioOptions[0]
                    isEditing.value = false

                }) { Text(text = "Clear") }

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp), onClick = {

                    if (userNameTxt.value.trim().isEmpty()) {
                        Toast.makeText(context, "Please enter name", Toast.LENGTH_LONG).show()
                    } else if (userEmailTxt.value.trim().isEmpty()) {
                        Toast.makeText(context, "Please enter email", Toast.LENGTH_LONG).show()
                    }
                    else {
                        onUpdateUser(
                            userDetail.userId.toString(), AddUser(
                                userName = userNameTxt.value,
                                userEmail = userEmailTxt.value,
                                userGender = userGenderTxt.value.lowercase(Locale.getDefault())))
                        isEditing.value = false


                    }

                }) { Text(text = "Update User") }

            }

        } else {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {

                if (userNameTxt.value.trim().isEmpty()) {
                    Toast.makeText(context, "Please enter name", Toast.LENGTH_LONG).show()
                } else if (userEmailTxt.value.trim().isEmpty()) {
                    Toast.makeText(context, "Please enter email", Toast.LENGTH_LONG).show()
                }
                else {
                    onAddUser(
                        AddUser(
                            userName = userNameTxt.value,
                            userEmail = userEmailTxt.value,
                            userGender = userGenderTxt.value.lowercase(Locale.getDefault())))
                    isEditing.value = false
                }

            }) { Text(text = "Add User") }
        }

    }
}

@Composable
fun UserListUI(userList: List<UserProfileModel>, onItemClick: (UserProfileModel) -> Unit = {}, onDeleteUser: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn() {
            items(userList) { _userDetail ->
                UserItemUI(_userDetail, onDeleteUser = { onDeleteUser(_userDetail.userId.toString()) }, onItemClick = { onItemClick(_userDetail) })
            }
        }
    }
}

@Composable
fun UserItemUI(data: UserProfileModel, onItemClick: () -> Unit = {}, onDeleteUser: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.White,
        elevation = 4.dp) {

        Row(modifier = Modifier.clickable { onItemClick() }) {
            Image(
                painter = painterResource(id = if (data.userGender == UserProfileViewModel.FEMALE) R.drawable.female else R.drawable.male),
                contentDescription = " user image",
                modifier = Modifier.size(100.dp))

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = data.userName.toString(),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = true)
                Text(
                    text = data.userEmail.toString(),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
                    modifier = Modifier.padding(top = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = true)

                Text(
                    text = data.status.toString().toCaps(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (data.status.toString() == UserProfileViewModel.ACTIVE) Color.Green else Color.Gray),
                    modifier = Modifier.padding(top = 8.dp))
            }
            Spacer(Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.delete_user), contentDescription = " Delete user",
//                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier
                    .padding(16.dp)
                    .size(15.dp)
                    .clickable { onDeleteUser() })
        }
    }
}
