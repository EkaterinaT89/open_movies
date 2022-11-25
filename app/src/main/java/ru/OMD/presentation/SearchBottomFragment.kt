package ru.OMD.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.OMD.databinding.FragmentBottomSearchBinding
import ru.OMD.utils.ItemClickListener
import ru.OMD.R
import ru.OMD.presentation.viewmodel.MovieViewModel

@AndroidEntryPoint
class SearchBottomFragment(private var mListener: ItemClickListener): BottomSheetDialogFragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentBottomSearchBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                progressBar.isVisible = state.loading
                if (state.error) {
                    errorMessage.visibility = View.VISIBLE
                    fmnTryAgainButton.setOnClickListener {
                        viewModel.tryAgain()
                        errorMessage.visibility = View.GONE
                    }
                }
            }
        }

            with(binding) {
                applyButton.setOnClickListener {
                    val title = titleForInput.text?.trim().toString()
                    val quantity = quantityForInput.text?.trim().toString()

                    if(title == "") {
                        val toast = Toast.makeText(
                            context,
                            R.string.error_title,
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                        toast.show()
                    } else if (quantity == "") {
                        val toast = Toast.makeText(
                            context,
                            R.string.error_quantity,
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                        toast.show()
                    } else {
                        viewModel.getMovies(title = title, size = quantity)

                        viewModel.movies.observe(viewLifecycleOwner) { movies ->
                            if(movies.Response == false) {
                                    val toast = Toast.makeText(
                                        context,
                                        movies.Error,
                                        Toast.LENGTH_LONG
                                    )
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                                    toast.show()
                                } else {
                                findNavController().navigate(R.id.listMoviesFragment)
                            }
                        }

                    }

                }

            }

        return binding.root
    }

}