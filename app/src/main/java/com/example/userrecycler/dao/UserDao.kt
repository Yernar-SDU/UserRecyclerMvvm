package com.example.userrecycler.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.userrecycler.model.User


@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("delete from users")
    fun deleteAllUsers()


    @Query("select * from users")
    fun getAllUsers(): LiveData<MutableList<User>>

    @Query("select * from users where age > 18 order by age asc")
    fun getAllUsersOver18(): LiveData<MutableList<User>>
}