package com.getloc.githublite.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.getloc.githublite.R
import com.getloc.githublite.ui.detail.tab.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*

@InternalCoroutinesApi
class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this, {
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

            tv_name.text = it.name
            tv_username.text = it.login
            tv_followers.text  = it.followers.toString()+" Followers"
            tv_following.text  = it.following.toString()+" Following"
            tv_location.text = it.location
        })

        var addFav = false
        CoroutineScope(Dispatchers.IO).launch{
            val notUserNull = viewModel.getCheckUserById(id)
            withContext(Dispatchers.Main){
                if(notUserNull != null){
                    if (notUserNull>0){
                        toggleFavorite.isChecked = true
                        addFav = true
                    } else{
                        toggleFavorite.isChecked = false
                        addFav = false
                    }
                }
            }
        }

        toggleFavorite.setOnClickListener {
            addFav =! addFav
            if (addFav){
                viewModel.addFavorite(id, username.toString(), avatarUrl.toString())
            } else
            {
                viewModel.removeFavorite(id)
            }
            toggleFavorite.isChecked = addFav
        }

    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_AVATAR_URL = "extra_avatar_url"
    }
}