package com.yarenyarsilikal.movieland.util

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.yarenyarsilikal.movieland.R


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */

@BindingAdapter("loadImageViewWithUrl")
fun ImageView.setImageViewWithUrl(url: String?) {
    if (url.isNullOrEmpty().not())
        Glide.with(context).load(TextUtils.concat(AppConstants.IMAGE_BASE_URL,url))
            .fitCenter()
            .into(this)
}

@BindingAdapter("dateText")
fun TextView.setTextWithDate(date: String?) {
    if (date.isNullOrEmpty().not())
        text = date?.formatDate("dd.mm.yyyy")
}

@BindingAdapter("rateText")
fun TextView.setTextWithRate(rateText: String) {
    val constant = SpannableString("/10")
    val constantColorSpan = ForegroundColorSpan(Color.rgb(173, 181, 189))
    constant.setSpan(
        constantColorSpan,
        0,
        constant.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = TextUtils.concat(rateText, constant)
}

@BindingAdapter("enableReverseDrawingOrder")
fun ViewPager.setPagerAnimation(enableReverseDrawingOrder: Boolean) {
    setPageTransformer(enableReverseDrawingOrder, ZoomOutPageTransformer())
}

