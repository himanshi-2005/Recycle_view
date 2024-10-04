package com.example.recycle_view.Recycle_view

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entity(
    @PrimaryKey(autoGenerate = true)

    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
)