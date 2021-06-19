@file:Suppress("SameParameterValue")

package com.getloc.githublite.ui.detail.tab.following

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getloc.githublite.R
import com.getloc.githublite.data.remote.response.User
import com.getloc.githublite.ui.detail.DetailActivity
import com.getloc.githublite.ui.main.MainAdapter
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FollowingFragment : Fragment() {

    private lateinit var adapter : MainAdapter
    private lateinit var viewModel : FollowingViewModel
    private lateinit var username : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()

        rv_follow.setHasFixedSize(true)
        rv_follow.layoutManager = LinearLayoutManager(activity)
        rv_follow.adapter = adapter
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        viewModel.setFollowing(username)
        viewModel.getFollowing().observe(viewLifecycleOwner, {
            if (it!=null){
                adapter.setList(it)
                progressbar(false)
            }
        })

        adapter.setSelectedUser(object : MainAdapter.OnItemClick{
            override fun onItemClicked(data: User) {
                Intent(requireContext(), DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_AVATAR_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })




    }

    private fun progressbar(state: Boolean){
        if (state){
            progress_Bar.visibility = View.VISIBLE
        } else {
            progress_Bar.visibility = View.GONE
        }
    }


}