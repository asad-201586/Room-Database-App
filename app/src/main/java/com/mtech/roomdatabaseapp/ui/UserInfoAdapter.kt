package com.mtech.roomdatabaseapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtech.roomdatabaseapp.databinding.ItemRoomDataBinding
import com.mtech.roomdatabaseapp.roomDB.entity.UserEntity

class UserInfoAdapter(private val listener: RawClickListener): RecyclerView.Adapter<UserInfoAdapter.UserViewHolder>() {

    var item = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>){
        this.item = data
    }

    class UserViewHolder(
        private val binding: ItemRoomDataBinding,
        private val listener: RawClickListener
        ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserEntity){
            binding.textName.text = data.name
            binding.textDesignation.text = data.designation
            binding.textAge.text = data.age.toString()

            binding.imageDelete.setOnClickListener{
                listener.onDeleteUserClickListener(data)
            }

            binding.imageEdit.setOnClickListener {
                listener.onEditUserClickListener(data)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemRoomDataBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(item[position])

        holder.itemView.setOnClickListener{
            listener.onItemClickListener(item[position])
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface RawClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
        fun onEditUserClickListener(user: UserEntity)
    }
}