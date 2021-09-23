package ru.unit6.course.android.retrofit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.FragmentUsers
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.model.User
import java.security.Key

class MainAdapter(private val users: ArrayList<User>, private val fragmentManager: FragmentManager?) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View, private val fragmentManager: FragmentManager?) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                val textViewUserName = this.findViewById<TextView>(R.id.textViewUserName)
                val textViewUserEmail = this.findViewById<TextView>(R.id.textViewUserEmail)
                val imageViewAvatar = this.findViewById<ImageView>(R.id.imageViewAvatar)
                this.findViewById<ConstraintLayout>(R.id.container).setOnClickListener {
                    val fragment = FragmentUsers()
                    val bundle = Bundle()
                    bundle.putString(KEY_ID, user.id.toString())
                    fragment.arguments = bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.add(R.id.container, fragment)?.addToBackStack("Stack")?.commit()
                }
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false), fragmentManager)

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            val fragment = FragmentUsers()
//            val bundle = Bundle()
//            bundle.putString(KEY_ID, users[position].toString())
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.add(R.id.container, fragment)?.addToBackStack("Stack")?.commit()
//        }
        holder.bind(users[position])
    }


    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    companion object {
        public const val KEY_ID = "id"
    }
}

