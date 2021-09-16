package com.mtech.roomdatabaseapp.roomDB.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employeeInfo")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "designation") val designation: String = "",
    @ColumnInfo(name = "age") val age: Int = 0

)