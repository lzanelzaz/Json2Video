package ru.lzanelzaz.json2video.repository

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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
    fun createProject(projectId: String) {
        val baseUrl =
            "https://firebasestorage.googleapis.com/v0/b/json2video-62dc8.appspot.com/o/"
        val srcList: List<String> = List(projectId.takeLastWhile { it != '_' }.toInt()) {
            baseUrl + "$projectId%2F$it".replace(":", "%3A") + "?alt=media"
        }

        val project = makeProject(srcList)
        Log.d("project", project.toString())
        val hashcode = getHashcode(project)
//        projectsDao.addProject(
//            projectId = projectId,
//            hashcode = hashcode,
//            project = project.toString()
//        )
        downloadProject(hashcode)
    }

    private fun makeProject(srcList: List<String>) = Project(
        resolution = Resolution.FULL_HD.stringValue,
        scenes = srcList.map { Scenes(elements = listOf(Video(src = it))) }
    )

    private fun getHashcode(project: Project): String {
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            Gson().toJson(project)
        )
        return apiService.getHashcode(body)
    }

    fun getProject(hashcode: String): String = projectsDao.getProject(hashcode)

    fun deleteProject(projectId: String) {
        projectsDao.deleteProject(projectId)
        Firebase.storage.reference.child(projectId).delete()
    }

    private fun downloadProject(hashcode: String) {
        val fileName = "$hashcode.mp4"

        val video = apiService.getStatus(hashcode).movie

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

        val request = DownloadManager.Request(Uri.parse(video.url))
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