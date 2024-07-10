package com.example.wallpaperapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.presentation.adapter.ImagesRecyclerViewAdapter
import com.example.wallpaperapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataset = arrayOf("January", "February", "March")
        val customAdapter = ImagesRecyclerViewAdapter(dataset)

        val recyclerView: RecyclerView = binding.imagesRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter


        val dataset2 = arrayOf("enero", "febrero", "marzo", "abril")

        binding.buttonUpdate.setOnClickListener {
        customAdapter.setItems(dataset2)
        }
    }
}
