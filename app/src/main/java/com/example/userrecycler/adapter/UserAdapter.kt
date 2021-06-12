package com.example.userrecycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userrecycler.MainActivity
import com.example.userrecycler.R
import com.example.userrecycler.model.User

class UserAdapter(context: MainActivity, users: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val mContext: Context = context
    private var mUsers: MutableList<User> = users




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val root: View = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false)
        return UserViewHolder(root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = mUsers[position]
        holder.userId.text = user.id.toString()
        holder.userAge.text = user.age.toString()
        holder.userName.text = user.name

    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.findViewById(R.id.userId)
        val userName: TextView = itemView.findViewById(R.id.userName)
        val userAge: TextView = itemView.findViewById(R.id.userAge)
    }

    fun setUserList(users: MutableList<User>){
        mUsers = users
        notifyDataSetChanged()
    }
}