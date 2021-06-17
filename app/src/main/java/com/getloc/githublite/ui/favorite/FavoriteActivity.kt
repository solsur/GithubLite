package com.getloc.githublite.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getloc.githublite.R
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.remote.response.User
import com.getloc.githublite.ui.detail.DetailActivity
import com.getloc.githublite.ui.main.MainAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.title = getString(R.string.fav)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        adapter.setSelectedUser(object : MainAdapter.OnItemClick{
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        rv_favorite.setHasFixedSize(true)
        rv_favorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        rv_favorite.adapter = adapter
        viewModel.readAllData.observe(this, {
            if (it!=null){
                val list = favoriteList(it)
                adapter.setList(list)
                progressbar(false)
            }
        })
    }

    private fun favoriteList(users: List<UserEntity>): ArrayList<User> {
        val listFavoriteUser= ArrayList<User>()
        for (user in users){
            val userList = User(
                user.username,
                user.id,
                user.avatar
            )
            listFavoriteUser.add(userList)
        }
        return listFavoriteUser
    }

    private fun progressbar(state: Boolean){
        if (state){
            progresbar.visibility = View.VISIBLE
        } else {
            progresbar.visibility = View.GONE
        }
    }
}