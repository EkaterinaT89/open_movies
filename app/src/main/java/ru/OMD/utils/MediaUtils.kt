package ru.OMD.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.OMD.R

object MediaUtils {

    fun moviePosterLoader(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .fitCenter()
            .error(R.drawable.ic_baseline_error_outline_24)
            .placeholder(R.drawable.ic_baseline_downloading_24)
            .timeout(10_000)
            .into(imageView)
    }

}