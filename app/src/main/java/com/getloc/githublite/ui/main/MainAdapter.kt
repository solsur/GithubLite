package com.getloc.githublite.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.getloc.githublite.R
import com.getloc.githublite.data.remote.response.User

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val list = ArrayList<User>()

    private lateinit var itemClick: OnItemClick

    fun setSelectedUser (onItemClick: OnItemClick){
        this.itemClick = onItemClick
    }

    fun setList(dataTravel: ArrayList<User>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            val id = view.findViewById<TextView>(R.id.tvID)
            val name = view.findViewById<TextView>(R.id.tvNameLogin)
            val imgUser = view.findViewById<ImageView>(R.id.img_user)

            id.text = "ID : "+ user.id.toString()
            name.text = user.login

            Glide.with(itemView.context)
                .load(user.avatar_url)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                )
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgUser)
            itemView.setOnClickListener {
                itemClick.onItemClicked(list[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MainViewHolder(v)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClick{
        fun onItemClicked(data:User)
    }


}