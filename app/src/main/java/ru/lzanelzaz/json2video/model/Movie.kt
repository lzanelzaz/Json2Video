package ru.lzanelzaz.json2video.model

data class Movie(
    val comment: String? = null,
    val draft: Boolean = true,
    val width: Int = 640, // 50 <= x <= 1920
    val height: Int = 360, // 50 <= x <= 1080
    val resolution: String = Resolution.SD.stringValue,
    val quality: String = Quality.LOW.stringValue,
    val fps: Int = 25,
    val scenes: String, // required, array of Scene
    val elements: String? = null, //array of any of these schemas: Video, Image, Text, HTML, Component, Template, Audio, Voice
    val cache: Boolean = true
)

enum class Resolution(val stringValue: String) {
    SD("sd"),
    HD("hd"),
    FULL_HD("full-hd"),
    INSTAGRAM_STORY("instagram-story"),
    INSTAGRAM_FEED("instagram-feed"),
    TWITTER_LANDSCAPE("twitter-landscape"),
    TWITTER_PORTRAIT("twitter-portrait")
}

enum class Quality(val stringValue: String) {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high")
}





