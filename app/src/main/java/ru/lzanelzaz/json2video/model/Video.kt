package ru.lzanelzaz.json2video.model

import com.google.gson.annotations.SerializedName
import java.net.URI

data class Video(
    val type: String = "video",
    val src: String, // URL to the asset file. Videos can be in MP4, MKV, MOV but MP4 is recommended.
    /*val comment: String? = null,
    val duration: Float = -1f, // Element's duration in seconds. A value of -1 auto calculates
    // the duration based on the asset intrinsic length or the scene duration.
    val start: Float = 0f, // Element's starting time in seconds relative to the container scene or
    // the movie if the element is in the Movie's elements array.
   *//* @SerializedName("extra-time")
    val extraTime: Float = 0f, // Element's time span added after the playback.
    *//*
    val cache: Boolean = true, // Element's cache policy. When true, the cached version (if exists)
    // is used. When false, the assets is downloaded.
    @SerializedName("fade-in")
    val fadeIn: Float = 0f, // 0 <= x, Adds a fade in effect to the element. Value in seconds.
    @SerializedName("fade-out")
    val fadeOut: Float = 0f, // 0 <= x, Adds a fade out effect to the element. Value in seconds.
    val x: Int = 0, // Sets the horizontal position of the element in the scene.
    val y: Int = 0, // Sets the vertical position of the element in the scene.
    //val scale: Scale? = null,
    //val rotate: Rotate? = null,
    //val crop: Crop? = null,
val muted: Boolean = false,
    val volume: Int = 5 // 0..10
    */
    )

data class Scale(
    val width: Int = -1, // -1 <= x
    val height: Int = -1 // -1 <= x
)

data class Rotate(
    val angle: Int = 0, // -360 <= x <= 360, Sets the angle of rotation
    val speed: Int = 0 // 0 <= x, Sets the time it takes to rotate the provided angle.
    // A zero value means no movement
)

data class Crop(
    val x: Int = 0,
    val y: Int = 0,
    val width: Int,
    val height: Int
)
