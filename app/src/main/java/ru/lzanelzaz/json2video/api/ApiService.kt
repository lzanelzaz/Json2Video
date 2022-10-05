package ru.lzanelzaz.json2video.api

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import ru.lzanelzaz.json2video.BuildConfig
import ru.lzanelzaz.json2video.model.Status

interface ApiService {
    // Use your own api key
    @Headers("x-api-key: " + BuildConfig.API_KEY)
    @POST("v2/movies")
    fun getHashcode(@Body body: RequestBody): String

    @Headers("x-api-key: " + BuildConfig.API_KEY)
    @GET("v2/movies")
    fun getStatus(@Query("project") projectHashcode: String): Status
}