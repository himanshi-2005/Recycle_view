package com.example.recycle_view.Recycle_view

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Interface {
    @Insert
    fun indertTodo(entity: Entity)

    @Query("SELECT * FROM Entity")
    fun getlist(): List<Entity>

    @Update
    fun updateTodo(entity: Entity)

    @Delete
    fun deleteTodo(entity: Entity)
}