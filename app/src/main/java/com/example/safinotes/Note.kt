package com.example.safinotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTables")
class Note(@ColumnInfo(name = "title") val noteTitle:String,

           @ColumnInfo(name = "description") val noteDescription:String,

            @ColumnInfo(name = "timestamp") val Timestamp:String){

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}