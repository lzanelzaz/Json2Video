package ru.lzanelzaz.json2video.bd

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Projects(
    @PrimaryKey
    val id: Int,
    @NonNull
    @ColumnInfo(name = "project_hashcode")
    val projectHashcode: String,
    @NonNull
    val url: String
)
