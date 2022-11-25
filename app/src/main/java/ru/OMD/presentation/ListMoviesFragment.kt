package ru.OMD.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.compose.ui.theme.OMDTheme
import dagger.hilt.android.AndroidEntryPoint
import ru.OMD.R
import ru.OMD.data.Movie
import ru.OMD.presentation.viewmodel.MovieViewModel
import ru.OMD.screen.ListMovies

@AndroidEntryPoint
class ListMoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            view.setContent {
                val navController = findNavController()
                OMDTheme {
                    ListMovies(
                        navController = navController, listMovies = movies.Search,
                        viewModel = viewModel
                    )
                }
            }

        }

        return view
    }
}