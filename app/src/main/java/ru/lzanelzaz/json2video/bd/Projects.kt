package ru.lzanelzaz.json2video.bd

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Projects(
    @PrimaryKey val id: Int,
    @NonNull val projectId: String,
    val hashcode: String,
    @NonNull val project: String
)
