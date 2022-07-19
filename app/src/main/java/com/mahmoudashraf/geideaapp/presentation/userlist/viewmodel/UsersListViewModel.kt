package com.mahmoudashraf.geideaapp.presentation.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudashraf.geideaapp.data.entity.User
import com.mahmoudashraf.geideaapp.domain.interactor.UsersInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val usersInterActor: UsersInterActor
) : ViewModel() {
    val screenState by lazy { MutableLiveData<UsersListScreenState>() }

    fun getAllUsers(){
        screenState.postValue(UsersListScreenState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = usersInterActor.getAllUsers().data
                screenState.postValue(UsersListScreenState.Success(result))
            } catch (exception: Exception) {
                screenState.postValue(
                    UsersListScreenState.Error(
                        message = exception.message ?: "Something wrong happened!"
                    )
                )
            }
        }
    }

}

sealed class UsersListScreenState {
    object Loading : UsersListScreenState()
    class Success(users: List<User>) : UsersListScreenState()
    class Error(message: String) : UsersListScreenState()
}