package ru.OMD.data.repository

import androidx.lifecycle.MutableLiveData
import ru.OMD.data.Search
import ru.OMD.domain.MovieApi
import ru.OMD.errors.ApiException
import ru.OMD.errors.NetWorkException
import ru.OMD.errors.UnknownException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApi
) : MovieRepository {

    override val movieData = MutableLiveData<Search>()

    override suspend fun getMovies(title: String, size: String) {
        try {
            val response = apiService.getMovies(title, size)
            movieData.value =
                response.body() ?: throw ApiException(response.code(), response.message())
            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

}