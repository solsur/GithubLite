@file:Suppress("DEPRECATION")

package com.getloc.githublite.ui.detail.tab

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.getloc.githublite.R
import com.getloc.githublite.ui.detail.tab.followers.FollowersFragment
import com.getloc.githublite.ui.detail.tab.following.FollowingFragment
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class SectionsPagerAdapter(private val context: Context, fragmentManager: FragmentManager, data : Bundle): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var bundle : Bundle = data

    private val tabLayoutTitle = intArrayOf(
        R.string.followers,
        R.string.following
    )

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments= this.bundle
        return  fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabLayoutTitle[position])
    }
}