package ru.lzanelzaz.json2video.repository

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import ru.lzanelzaz.json2video.api.ApiService
import ru.lzanelzaz.json2video.bd.ProjectsDao
import ru.lzanelzaz.json2video.model.Project
import ru.lzanelzaz.json2video.model.Resolution
import ru.lzanelzaz.json2video.model.Scenes
import ru.lzanelzaz.json2video.model.Video
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val apiService: ApiService,
    private val projectsDao: ProjectsDao
) {
    suspend fun getStatus(projectHashcode: String): String = apiService.getStatus(projectHashcode)

    fun createProject(): Project {
        val src1 = "https://assets.json2video.com/assets/videos/beach-01.mp4"
        val src2 = "https://assets.json2video.com/assets/videos/beach-02.mp4"
        return Project(
            resolution = Resolution.FULL_HD.stringValue,
            scenes = listOf(
                Scenes(comment = "Scene #1", elements = listOf(Video(src = src1))),
                Scenes(elements = listOf(Video(src = src2)))
            )
        )
    }

    suspend fun renderProject(project: Project): String {
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            Gson().toJson(project)
        )
        return apiService.renderProject(body)
    }

    fun addProjectToDb() {
        //"success": true
        TODO("apiService.getStatus()")
        //project -> hashcode
        TODO("renderProject()")
        //movie -> url
        TODO("projectsDao.addProject()")
    }

    suspend fun downloadProject(projectHashcode: String) {
//        val url =
//            URL("https://assets.json2video.com/clients/axea6t84y4/renders/2022-09-06-91325.mp4")
        projectsDao.getUrl(projectHashcode)
        TODO("")
    }
}