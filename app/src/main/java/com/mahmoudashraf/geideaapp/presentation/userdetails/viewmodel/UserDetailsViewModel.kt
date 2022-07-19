package com.mahmoudashraf.geideaapp.presentation.userdetails.viewmodel

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
class UserDetailsViewModel @Inject constructor(
    private val usersInterActor: UsersInterActor
) : ViewModel() {
    var backRequired: Boolean = false
    var counter: Int = 5
    val screenState by lazy { MutableLiveData<UsersDetailsScreenState>() }

   fun getUsers(userId : Int){
        screenState.postValue(UsersDetailsScreenState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = usersInterActor.getUserByID(userId).data
                screenState.postValue(UsersDetailsScreenState.Success(result))
            } catch (exception: Exception) {
                screenState.postValue(
                    UsersDetailsScreenState.Error(
                        message = exception.message ?: "Something wrong happened!"
                    )
                )
            }
        }
    }

}

sealed class UsersDetailsScreenState {
    object Loading : UsersDetailsScreenState()
    data class Success(val user: User) : UsersDetailsScreenState()
    data class Error(val message: String) : UsersDetailsScreenState()
}