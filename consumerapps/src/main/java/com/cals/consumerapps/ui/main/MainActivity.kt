package com.cals.consumerapps.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cals.consumerapps.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val rv_main:RecyclerView = findViewById(R.id.rv_main)
        rv_main.setHasFixedSize(true)
        rv_main.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_main.adapter = adapter
        viewModel.setUser(this)
        viewModel.getUser()?.observe(this, {
            if (it!=null){
                adapter.setList(it)
            }
        })
    }
}