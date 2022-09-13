package ru.lzanelzaz.json2video.bd

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProjectsDao {
    @Query("SELECT url FROM projects WHERE project_hashcode = :projectHashcode")
    fun getUrl(projectHashcode: String): String

    @Query("INSERT INTO projects (id, project_hashcode, url) VALUES(null, :projectHashcode, :url)")
    fun addProject(projectHashcode: String, url: String)
}