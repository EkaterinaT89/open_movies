package ru.OMD.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.OMD.databinding.FragmentCallToActionBinding
import ru.OMD.utils.ItemClickListener
import ru.OMD.utils.SearchBottomSheet

@AndroidEntryPoint
class CallToActionFragment : Fragment(), ItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCallToActionBinding.inflate(
            inflater,
            container,
            false
        )


        with(binding) {
            searhButton.setOnClickListener {
                openBottomSheetSearch()
            }

        }


        return binding.root
    }

    private fun openBottomSheetSearch() {
        val verify = SearchBottomSheet.newInstance(this)
        verify.show(
            childFragmentManager, SearchBottomSheet.TAG
        )
    }

    override fun onItemClick(item: String?) {

    }

}