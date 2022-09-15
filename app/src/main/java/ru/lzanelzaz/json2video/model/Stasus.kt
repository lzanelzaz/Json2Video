package ru.lzanelzaz.json2video.model

import com.google.gson.annotations.SerializedName

data class Status(
    val success: Boolean,
    val movie: MovieStatus,
    @SerializedName("remaining_quota")
    val remainingQuota: RemainingQuota
)

data class MovieStatus(
    val url: String,
    val duration: Int,
    val size: Int,
    val width: Int,
    val height: Int
)

data class RemainingQuota(
    val movies: Int,
    val drafts: Int
)
