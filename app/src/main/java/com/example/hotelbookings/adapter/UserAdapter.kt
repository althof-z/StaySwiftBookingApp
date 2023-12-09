package com.example.hotelbookings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.R
import com.example.hotelbookings.data.User

class UserAdapter(private val userList: List<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_card, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        // Set data pengguna ke dalam views pada card
        holder.usernameTextView.text = user.username
        holder.emailTextView.text = user.email
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.textView7)
        val emailTextView: TextView = itemView.findViewById(R.id.textView8)
        // Tambahkan views lainnya sesuai kebutuhan
    }
}