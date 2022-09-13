package ru.lzanelzaz.json2video.bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Projects::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectsDao(): ProjectsDao
}