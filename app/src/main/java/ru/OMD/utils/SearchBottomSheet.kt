package ru.OMD.utils

import ru.OMD.presentation.SearchBottomFragment

class SearchBottomSheet {

    companion object{
        const val TAG = "SearchBottomSheet"
        fun newInstance(mListener:ItemClickListener): SearchBottomFragment {

            return SearchBottomFragment(mListener)
        }
    }

}