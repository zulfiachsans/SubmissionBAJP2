package com.dicoding.submissionjetpack2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.submissionjetpack2.R
import com.dicoding.submissionjetpack2.ui.movies.MoviesFragment
import com.dicoding.submissionjetpack2.ui.tvshow.TvFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carouselView.pageCount = carouselImages.size
        carouselView.setImageListener(imageListener)

        val fragmentList = listOf(MoviesFragment(), TvFragment())
        val tabTitle = listOf("MOVIES", "TVSHOWS")
        viewpager.adapter =
            SectionPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout2, viewpager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    val carouselImages = intArrayOf(
        R.drawable.cruella,
        R.drawable.the_conjuring,
        R.drawable.army,
        R.drawable.miraculous,
        R.drawable.loki,
        R.drawable.lucifer,
        R.drawable.ragnarok,
        R.drawable.invincible
    )

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(carouselImages[position])
    }

}