package com.mtech.roomdatabaseapp.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mtech.roomdatabaseapp.roomDB.dao.UserDAO
import com.mtech.roomdatabaseapp.roomDB.entity.UserEntity

@Database(entities = [UserEntity::class],version = 1)
abstract class RoomDB: RoomDatabase() {

    abstract fun userDao(): UserDAO?

    companion object{
        private var INSTANCE: RoomDB? = null

        fun getAppDatabase(context: Context): RoomDB?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,RoomDB::class.java,"AppDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE
        }
    }

}