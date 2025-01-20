package com.example.examennadirelhaidouri.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Furnitures")
data class  Mobles(
    @ColumnInfo(name = "name")
    val nom:String,

    @ColumnInfo(name = "price")
    val preu:Float,

){
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}
