package com.yarenyarsilikal.movieland.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.yarenyarsilikal.movieland.data.model.response.MovieResponse
import com.yarenyarsilikal.movieland.databinding.ItemNowPlayingBinding


/**
 * Created by yarenyarsilikal on 12.02.2022.
 */
class NowPlayingViewPagerAdapter(
    private val movies: List<MovieResponse>,
    private val viewPager: ViewPager?,
    private val onItemClick: ((Int) -> Unit)?
) : PagerAdapter() {
    override fun getCount(): Int = movies.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemNowPlayingBinding.inflate(
            container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            container,
            false,
        )
        container.addView(binding.root)
        binding.movie = movies[position]
        viewPager?.let { binding.dotsIndicator.setViewPager(it) }
        binding.root.setOnClickListener { onItemClick?.invoke(movies[position].id) }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }
}