package ru.OMD.data

data class Movie(
    val Title: String? = null,
    val Year: String? = null,
    val Type: String? = null,
    val Poster: String? = null,
    val imdbID: String? = null,
    var like: Boolean = false,
    var commentCount: Int = 0
)
