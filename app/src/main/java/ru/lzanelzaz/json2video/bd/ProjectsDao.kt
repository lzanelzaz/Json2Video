package ru.lzanelzaz.json2video.bd

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProjectsDao {
    @Query("INSERT INTO projects (id, projectId, hashcode, project) VALUES(null, :projectId, :hashcode, :project)")
    fun addProject(projectId: String, hashcode: String, project: String)

    @Query("SELECT project FROM projects WHERE hashcode = :hashcode")
    fun getProject(hashcode: String): String

    @Query("DELETE FROM projects WHERE projectId = :projectId")
    fun deleteProject(projectId: String)
}