package com.mtech.roomdatabaseapp.roomDB.dao

import androidx.room.*
import com.mtech.roomdatabaseapp.roomDB.entity.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM employeeInfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>?

    @Insert
    fun addUser(user: UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user: UserEntity?)

}