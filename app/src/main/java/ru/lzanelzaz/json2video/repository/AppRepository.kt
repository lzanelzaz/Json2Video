package ru.lzanelzaz.json2video.repository

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
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
    private val projectsDao: ProjectsDao,
    private val appContext: Context
) {
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

    fun downloadProject(projectHashcode: String) {
        val fileName = "$projectHashcode.mp4"

        val url = projectsDao.getUrl(projectHashcode)
        val video = apiService.getStatus(projectHashcode).movie

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_DOWNLOADS
            )
            put(MediaStore.MediaColumns.DURATION, video.duration * 1000)
            put(MediaStore.MediaColumns.SIZE, video.size)
            put(MediaStore.MediaColumns.HEIGHT, video.height)
            put(MediaStore.MediaColumns.WIDTH, video.width)
            put(MediaStore.MediaColumns.DATE_MODIFIED, System.currentTimeMillis() / 1000)
            put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000)
            put(MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis())
        }
        appContext.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setDescription("Downloading...")
            .setMimeType("video/mp4")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val manager: DownloadManager =
            appContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}