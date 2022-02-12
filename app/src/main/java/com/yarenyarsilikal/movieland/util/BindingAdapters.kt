package com.yarenyarsilikal.movieland.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */

@BindingAdapter("loadImageViewWithUrl")
fun ImageView.setImageViewWithUrl(url: String?) {
    if (url.isNullOrEmpty().not())
        Glide.with(context).load(AppConstants.IMAGE_BASE_URL.plus(url))
            .fitCenter()
            .into(this)
}

@BindingAdapter("dateText")
fun TextView.setTextWithDate(date: String) {
    text = date.formatDate("dd.mm.yyyy")
}

