package com.delta22.rentateamtest.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delta22.rentateamtest.R
import com.delta22.rentateamtest.data.json.User
import com.delta22.rentateamtest.viewholder.UserViewHolder
import java.util.*

class UsersAdapter(private val navController: NavController) : RecyclerView.Adapter<UserViewHolder>() {

    private val listOfUsers = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.textFirstName.text = listOfUsers[position].firstName
        holder.textLastName.text = listOfUsers[position].lastName

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(User.USER_EXTRA, listOfUsers[position])
            navController.navigate(R.id.navigation_user_info, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    fun updatePlaylists(plsts: List<User>) {
        val diffResult = DiffUtil.calculateDiff(PlaylistDiffUtilCallback(listOfUsers, plsts))
        listOfUsers.clear()
        listOfUsers.addAll(plsts)
        diffResult.dispatchUpdatesTo(this)
    }

    private class PlaylistDiffUtilCallback(
        private val oldList: List<User>,
        private val newList: List<User>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition] == newList[newItemPosition])
        }
    }
}
