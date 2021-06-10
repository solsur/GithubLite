package com.getloc.githublite.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.getloc.githublite.R
import com.getloc.githublite.ui.detail.tab.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val extras = intent.extras
        val username = extras?.getString(EXTRA_USERNAME)

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
            viewModel.getUserDetail().observe(this, {
                tv_name.text = it.name
                tv_username.text = it.login
                tv_followers.text  = it.followers.toString()+" Followers"
                tv_following.text  = it.following.toString()+" Following"
                tv_location.text = it.location
                Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .transform(RoundedCorners(20))
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loader)
                                        .error(R.drawable.ic_error)
                        )
                        .centerCrop()
                        .into(iv_profile)
            })
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}