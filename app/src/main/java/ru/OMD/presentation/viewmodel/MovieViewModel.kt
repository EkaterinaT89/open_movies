package ru.OMD.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.OMD.data.Movie
import ru.OMD.data.Search
import ru.OMD.data.repository.MovieRepository
import ru.OMD.presentation.model.StateViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _dataState = MutableLiveData<StateViewModel>()
    val dataState: LiveData<StateViewModel>
        get() = _dataState

    val movies = repository.movieData

    private var titleMovie: String? = null
    private var sizeMovie: String? = null

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie>
        get() = _currentMovie

    val emptyMovie = Search(Error = "")

    fun cleanMovie() {
        movies.value = emptyMovie
    }

    fun getMovies(title: String, size: String) {
        titleMovie = title
        sizeMovie = size
        try {
            viewModelScope.launch {
                _dataState.value = StateViewModel(loading = true)
                repository.getMovies(title, size)
                _dataState.value = StateViewModel()

            }
        } catch (e: Exception) {
            _dataState.value = StateViewModel(error = true)
        }
    }

    fun tryAgain() {
        titleMovie?.let{
            sizeMovie?.let {
                getMovies(titleMovie!!, sizeMovie!!)
            }
        }
    }

    fun liked(movie: Movie) {
        movie.like = true
    }

    fun disLiked(movie: Movie) {
        movie.like = false
    }

    fun getCurrentMovie(movie: Movie) {
        _currentMovie.value = movie
    }

}