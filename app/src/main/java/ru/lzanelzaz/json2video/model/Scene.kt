package ru.lzanelzaz.json2video.model

import com.google.gson.annotations.SerializedName

data class Scenes(
    val comment: String? = null,
    /*@SerializedName("background-color")
    val backgroundColor: String = "#000000",
    val duration: Float = -1f,*/
    val elements: List<Video>/* , //  Video, Image, Text, HTML, Component, Audio, Voice
   val transition: Transition? = null,
    val cache: Boolean = true*/
)

data class Transition(
    val type: String = "xfade",
    val style: String = Style.FADE.stringValue,
    val duration: Float = 1.5f
)

enum class Style(val stringValue: String) {
    FADE("fade"),
    WIPELEFT("wipeleft"),
    WIPERIGHT("wiperight"),
    WIPEUP("wipeup"),
    WIPEDOWN("wipedown"),
    SLIDELEFT("slideleft"),
    SLIDERIGHT("slideright"),
    SLIDEUP("slideup"),
    SLIDEDOWN("slidedown"),
    CIRCLECROP("circlecrop"),
    RECTCROP("rectcrop"),
    DISTANCE("distance"),
    FADEBLACK("fadeblack"),
    FADEWHITE("fadewhite"),
    RADIAL("radial"),
    SMOOTHLEFT("smoothleft"),
    SMOOTHRIGHT("smoothright"),
    SMOOTHUP("smoothup"),
    SMOOTHDOWN("smoothdown"),
    CIRCLEOPEN("circleopen"),
    CIRCLECLOSE("circleclose"),
    VERTOPEN("vertopen"),
    VERTCLOSE("vertclose"),
    HORZOPEN("horzopen"),
    HORZCLOSE("horzclose"),
    DISSOLVE("dissolve"),
    PIXELIZE("pixelize"),
    DIAGTL("diagtl"),
    DIAGTR("diagtr"),
    DIAGBL("diagbl"),
    DIAGBR("diagbr"),
    HLSLICE("hlslice"),
    HRSLICE("hrslice"),
    VUSLICE("vuslice"),
    VDSLICE("vdslice"),
    HBLUR("hblur"),
    FADEGRAYS("fadegrays"),
    WIPETL("wipetl"),
    WIPETR("wipetr"),
    WIPEBL("wipebl"),
    WIPEBR("wipebr"),
    SQUEEZEH("squeezeh"),
    SQUEEZEV("squeezev"),
    ZOOMIN("zoomin")
}