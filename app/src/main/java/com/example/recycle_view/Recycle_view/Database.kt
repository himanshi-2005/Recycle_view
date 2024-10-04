package com.example.recycle_view.Recycle_view

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 1, exportSchema = true)
   abstract class MyDatabase:RoomDatabase() {
  abstract fun  myInterface():Interface
companion object{
  private  var database:MyDatabase?=null

    fun getInstance(context: Context):MyDatabase{
if(database==null){
    database= Room.databaseBuilder(context,
        MyDatabase::class.java,
        "Database")
        .allowMainThreadQueries()
        .build()
}
        return database!!
    }
}
}
