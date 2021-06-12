package com.example.userrecycler.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.userrecycler.dao.UserDao
import com.example.userrecycler.database.UserDatabase
import com.example.userrecycler.model.User

class UserRepository(application: Application) {
    var userDao: UserDao
    var allUsers: LiveData<ArrayList<User>>
    var allUsersOver18: LiveData<ArrayList<User>>

    init {
         val userDatabase: UserDatabase = UserDatabase.getInstance(application)
        userDao = userDatabase.userDao()
        allUsers = userDao.getAllUsers()
        allUsersOver18 = userDao.getAllUsersOver18()
    }


    fun insert(user: User){
        InsertUserAsyncTask(userDao).execute(user)
    }

    fun update(user: User){
        UpdateUserAsyncTask(userDao).execute(user)
    }

    fun delete(user: User){
        DeleteUserAsyncTask(userDao).execute(user)
    }

    fun deleteAllUsers(){
        DeleteAllUserAsyncTask(userDao).execute()
    }

    fun getAllUser(): LiveData<ArrayList<User>>{
        return allUsers
    }

    fun getAllUserOver18(): LiveData<ArrayList<User>>{
        return allUsersOver18
    }


    companion object{
        class InsertUserAsyncTask(val userDao: UserDao) : AsyncTask<User, Void, Unit>() {

            override fun doInBackground(vararg params: User): Unit {
                userDao.insert(params[0])
            }
        }
        class UpdateUserAsyncTask(val userDao: UserDao) : AsyncTask<User, Void, Unit>() {

            override fun doInBackground(vararg params: User): Unit {
                userDao.update(params[0])
            }
        }
        class DeleteUserAsyncTask(val userDao: UserDao) : AsyncTask<User, Void, Unit>() {

            override fun doInBackground(vararg params: User): Unit {
                userDao.delete(params[0])
            }
        }

        class DeleteAllUserAsyncTask(val userDao: UserDao) : AsyncTask<User, Void, Unit>() {

            override fun doInBackground(vararg params: User): Unit {
                userDao.deleteAllUsers()
            }
        }
    }

}