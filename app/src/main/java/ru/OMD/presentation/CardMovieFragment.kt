package ru.OMD.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.OMD.databinding.FragmentCardMovieBinding
import ru.OMD.presentation.viewmodel.MovieViewModel
import ru.OMD.R
import ru.OMD.utils.MediaUtils
import androidx.browser.customtabs.CustomTabsIntent

private const val LINK_BASE = "https://www.imdb.com/title/"

@AndroidEntryPoint
class CardMovieFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCardMovieBinding.inflate(
            inflater,
            container,
            false
        )

        with(binding) {
            viewModel.currentMovie.observe(viewLifecycleOwner) { movie ->
                year.text = movie.Year
                type.text = movie.Type
                title.text = movie.Title

                if (movie.commentCount == 0) {
                    comment.text = "IMDb"
                } else {
                    comment.text = movie.commentCount.toString()
                }

                if (movie.like) {
                    liked.visibility = View.VISIBLE
                    like.visibility = View.GONE
                } else {
                    liked.visibility = View.GONE
                    like.visibility = View.VISIBLE
                }

                like.setOnClickListener {
                    viewModel.liked(movie)
                    like.visibility = View.GONE
                    liked.visibility = View.VISIBLE
                }

                liked.setOnClickListener {
                    viewModel.disLiked(movie)
                    like.visibility = View.VISIBLE
                    liked.visibility = View.GONE
                }

                leaveCommentButton.setOnClickListener {
                    movie.commentCount = movie.commentCount + 1
                    comment.text = movie.commentCount.toString()
                    commentForInput.text = null
                }

                backButton.setOnClickListener {
                    findNavController().navigate(R.id.listMoviesFragment)
                }

                MediaUtils.moviePosterLoader(poster, movie.Poster)

                link.setOnClickListener {
                    CustomTabsIntent.Builder()
                        .setShowTitle(true)
                        .build()
                        .launchUrl(requireContext(), Uri.parse(LINK_BASE + movie.imdbID + "/"))
                }

            }
        }

        return binding.root
    }

}