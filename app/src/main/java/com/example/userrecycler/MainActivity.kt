package com.example.userrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userrecycler.adapter.UserAdapter
import com.example.userrecycler.model.User
import com.example.userrecycler.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewByIds()
        initializeRecyclerView()
        initializeViewModels()

    }


    private fun findViewByIds() {
        userRecyclerView = findViewById(R.id.usersRecyclerView)
    }

    private fun initializeRecyclerView() {
        userAdapter = UserAdapter(this, arrayListOf())
        userRecyclerView.adapter = userAdapter
        userRecyclerView.layoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
    }

    private fun initializeViewModels() {
        userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        userViewModel.getAllUsersOver18().observe(this, {
                users ->
            Log.i("tag",users.toString())
            userAdapter.setUserList(users)
        })

    }

}