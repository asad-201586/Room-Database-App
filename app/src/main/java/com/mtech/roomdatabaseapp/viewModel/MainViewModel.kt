package com.mtech.roomdatabaseapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mtech.roomdatabaseapp.roomDB.RoomDB
import com.mtech.roomdatabaseapp.roomDB.entity.UserEntity

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val userLiveData = MutableLiveData<List<UserEntity>>()

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>>{
        return userLiveData
    }

    fun getAllUsers(){
        val userDao = RoomDB.getAppDatabase(getApplication())?.userDao()
        val list = userDao?.getAllUserInfo()
        userLiveData.postValue(list!!)
    }

    fun addUser(user: UserEntity){
        val userDao = RoomDB.getAppDatabase(getApplication())?.userDao()
        userDao?.addUser(user)
        getAllUsers()
    }

    fun updateUser(user: UserEntity){
        val userDao = RoomDB.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(user)
        getAllUsers()
    }

    fun deleteUser(user: UserEntity){
        val userDao = RoomDB.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(user)
        getAllUsers()
    }

}