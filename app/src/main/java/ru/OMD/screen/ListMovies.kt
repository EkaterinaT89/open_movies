package ru.OMD.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.compose.ui.theme.darkTint
import ru.OMD.R
import ru.OMD.components.BackButton
import ru.OMD.components.ItemMovie
import ru.OMD.data.Movie
import ru.OMD.presentation.viewmodel.MovieViewModel

@Composable
fun ListMovies(navController: NavController, listMovies: List<Movie>, viewModel: MovieViewModel) {

    Surface {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                BackButton(navController = navController, viewModel)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.movies), color = darkTint)
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                List(listMovies = listMovies, viewModel = viewModel, navController)
            }
        }

    }

}

@Composable
fun List(listMovies: List<Movie>, viewModel: MovieViewModel, navController: NavController) {
    LazyColumn {
        itemsIndexed(items = listMovies) { _, item ->
            ItemMovie(
                counter = item.commentCount.toString(),
                title = item.Title.toString(),
                url = item.Poster.toString(),
                year = item.Year.toString(),
                type = item.Type.toString(),
                viewModel = viewModel,
                movie = item,
                navController
            )
        }
    }
}

