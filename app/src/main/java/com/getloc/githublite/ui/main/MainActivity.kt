@file:Suppress("SameParameterValue")

package com.getloc.githublite.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getloc.githublite.R
import com.getloc.githublite.data.remote.response.User
import com.getloc.githublite.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener {
            searchUser()
        }

        et_query.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        adapter.setSelectedUser(object : MainAdapter.OnItemClick{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        rv_search.setHasFixedSize(true)
        rv_search.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_search.adapter = adapter
        viewModel.getSearchUser().observe(this, {
            if (it!=null){
                adapter.setList(it)
                progressbar(false)
            }
        })


    }

    private fun progressbar(state: Boolean){
        if (state){
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun searchUser(){

        val query = et_query.text.toString()
        if (query.isEmpty()) return
        progressbar(true)
        viewModel.setSearchUser(query)
    }

}