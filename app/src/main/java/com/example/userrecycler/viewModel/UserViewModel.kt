package com.example.userrecycler.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.userrecycler.model.User
import com.example.userrecycler.repository.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository = UserRepository(application)

    fun insert(user: User){
        repository.insert(user)
    }

    fun update(user: User){
        repository.update(user)
    }

    fun delete(user: User){
        repository.delete(user)
    }

    fun deleteAllUsers(){
        repository.deleteAllUsers()
    }

    fun getAllUsers(): LiveData<MutableList<User>> {
        return repository.getAllUser()
    }

    fun getAllUsersOver18(): LiveData<MutableList<User>>{
        return repository.getAllUserOver18()
    }

}