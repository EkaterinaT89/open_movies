package ru.OMD.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.ui.theme.darkTint
import ru.OMD.R
import ru.OMD.presentation.viewmodel.MovieViewModel

@Composable
fun BackButton(navController: NavController, viewModel: MovieViewModel) {
    IconButton(onClick = { navController.navigate(R.id.callToActionFragment)
                            viewModel.cleanMovie()}) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),
            contentDescription = "button",
            tint = darkTint,
            modifier = Modifier
                .size(22.dp)
        )
    }
}