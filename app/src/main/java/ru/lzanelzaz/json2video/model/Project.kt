package ru.lzanelzaz.json2video.model

data class Project(
    val resolution: String = Resolution.SD.stringValue,
    val scenes: List<Scenes>
)