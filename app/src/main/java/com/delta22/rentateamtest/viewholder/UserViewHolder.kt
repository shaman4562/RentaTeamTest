package com.delta22.rentateamtest.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delta22.rentateamtest.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textFirstName: TextView = itemView.findViewById(R.id.text_info_user_first_name)
    var textLastName: TextView = itemView.findViewById(R.id.text_info_user_last_name)
}
