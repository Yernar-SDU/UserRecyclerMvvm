package com.example.userrecycler.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.userrecycler.dao.UserDao
import com.example.userrecycler.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase :RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase{
            instance = Room.databaseBuilder(
                context,
                UserDatabase::class.java, "user_database"
            ).fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        PopulateDbAsyncTask(instance).execute()
                    }
                })
                .build()
            return instance as UserDatabase
        }
    }


    class PopulateDbAsyncTask(db: UserDatabase?) : AsyncTask<User, Void, Unit>() {
        private val noteDao: UserDao = db!!.userDao()
        override fun doInBackground(vararg params: User): Unit {
            noteDao.insert(User(name = "Almas",age = 20))
            noteDao.insert(User(name = "Arnur",age = 12))
            noteDao.insert(User(name = "Azamat",age = 15))
            noteDao.insert(User(name = "Aydar",age = 19))
            noteDao.insert(User(name = "Akerke",age = 25))
            noteDao.insert(User(name = "Aray",age = 30))
        }
    }
}