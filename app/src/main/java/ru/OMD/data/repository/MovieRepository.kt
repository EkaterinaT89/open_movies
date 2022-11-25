package ru.OMD.data.repository

import androidx.lifecycle.MutableLiveData
import ru.OMD.data.Search

interface MovieRepository {

    val movieData: MutableLiveData<Search>
    suspend fun getMovies(title: String, size: String)

}