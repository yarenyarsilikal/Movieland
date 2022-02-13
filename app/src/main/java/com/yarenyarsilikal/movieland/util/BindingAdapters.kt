package com.yarenyarsilikal.movieland.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.screen.home.adapter.NowPlayingViewPagerAdapter
import com.yarenyarsilikal.movieland.screen.home.adapter.UpComingMovieAdapter


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */

    @BindingAdapter("loadImageViewWithUrl")
    fun ImageView.setImageViewWithUrl(url: String?) {
        if (url.isNullOrEmpty().not())
            Glide.with(context).load(TextUtils.concat(AppConstants.IMAGE_BASE_URL, url))
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

    @BindingAdapter("refreshListener")
    fun SwipeRefreshLayout.setOnRefreshListener(higher: (() -> Unit)) {
        setOnRefreshListener {
            higher.invoke()
            isRefreshing = false
        }
    }

    @BindingAdapter("rvList", "onItemClick")
    fun RecyclerView.setRecyclerViewAdapter(
        list: List<MovieResponse>?,
        onClickListener: ((Int) -> Unit)?
    ) {
        if (adapter == null) {
            list?.let {
                adapter = UpComingMovieAdapter(list, onClickListener)
            }
        }
    }

    @BindingAdapter("vpList", "onItemClick")
    fun ViewPager.setPagerAdapter(
        movies: List<MovieResponse>?,
        onItemClick: ((Int) -> Unit)
    ) {
        if (adapter == null) {
            movies?.let {
                 val nowPlayingAdaper = NowPlayingViewPagerAdapter(movies, this) {
                    onItemClick.invoke(it)
                }
                adapter = nowPlayingAdaper
                setPageTransformer(true, ZoomOutPageTransformer())
            }
        }
    }

