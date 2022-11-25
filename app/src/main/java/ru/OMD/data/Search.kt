package ru.OMD.data

data class Search(
    val Search: List<Movie> = emptyList(),
    val Response: Boolean = false,
    val Error: String? = null
)