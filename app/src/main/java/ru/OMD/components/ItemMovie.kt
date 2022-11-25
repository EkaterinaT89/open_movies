package ru.OMD.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.ui.theme.*
import ru.OMD.R
import ru.OMD.data.Movie
import ru.OMD.presentation.viewmodel.MovieViewModel

@Composable
fun ItemMovie(
    counter: String, title: String, url: String, year: String, type: String,
    viewModel: MovieViewModel, movie: Movie, navController: NavController
) {

    val liked = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                viewModel.getCurrentMovie(movie); navController.navigate(R.id.cardMovieFragment)
            },
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        shadowElevation = 10.dp
    ) {

        liked.value = movie.like

        MovieCard(title, url, year, type)
        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
            ButtonCard(liked, counter, viewModel, movie)
        }

    }

}

@Composable
fun MovieCard(movieTitle: String, url: String, year: String, type: String) {
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .placeholder(R.color.grey_color)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 100.dp, height = 180.dp)
                .padding(all = 10.dp)
        )
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Text(
                text = movieTitle,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = darkTint,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(text = year, fontSize = 14.sp, color = darkGrey)

            Spacer(Modifier.height(15.dp))
            Text(
                text = type, fontSize = 12.sp, color = darkGrey,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
            )
        }
    }


}

@Composable
fun ButtonCard(
    likeState: MutableState<Boolean>,
    commentCounter: String,
    viewModel: MovieViewModel,
    movie: Movie
) {
    Spacer(modifier = Modifier.width(5.dp))
    IconButton(onClick = { }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_mode_comment_24),
            contentDescription = stringResource(id = R.string.comment),
            tint = lightTint,
            modifier = Modifier.size(20.dp)
        )
        Text(text = commentCounter, color = lightTint, modifier = Modifier.padding(start = 35.dp))
    }
    Spacer(modifier = Modifier.width(5.dp))
    IconButton(
        onClick = { likeState.value = !likeState.value },
        modifier = Modifier.padding(end = 8.dp)
    ) {
        if (likeState.value) {
            viewModel.liked(movie)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                tint = Color.Red,
                contentDescription = stringResource(id = R.string.liked),
                modifier = Modifier.size(20.dp)
            )
        } else {
            viewModel.disLiked(movie)
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                tint = lightTint,
                contentDescription = stringResource(id = R.string.disliked),
                modifier = Modifier.size(20.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ItemMoviePreview() {
//    ItemMovie()
}