package com.example.wallpaperapp.presentation.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.wallpaperapp.presentation.adapter.ImagesRecyclerViewAdapter
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.domain.entity.WallpaperLink
import com.example.wallpaperapp.presentation.WallPaperUiState
import com.example.wallpaperapp.presentation.viewmodel.WallPaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private val viewModel: WallPaperViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        collectState()
        viewModel.fetchWallPapers()

        /* 1. Setup Views
        2. Collect the state -> no wallpaper in the beginning
        3. update the wallpaper from the rest api
        4. update the ui */

//        val dataset = arrayOf("January", "February", "March")
//        val customAdapter = ImagesRecyclerViewAdapter(dataset)
//
//        val recyclerView: RecyclerView = binding.imagesRecyclerView
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = customAdapter
//
//
//        val dataset2 = arrayOf("enero", "febrero", "marzo", "abril")
//
//        binding.buttonUpdate.setOnClickListener {
//        customAdapter.setItems(dataset2)

    }

    private fun setupViews() {
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(context , 2 )

        }
    }

    private fun collectState() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.wallpaperList.collect {
                wallpaperUiState ->
                when(wallpaperUiState) {
                    is WallPaperUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity , "Wallpapers are loading", Toast.LENGTH_SHORT).show()

                    }
                    is WallPaperUiState.EmptyList -> {
                        binding.progressBar.visibility = View.VISIBLE

                        Toast.makeText(this@MainActivity , "Wallpapers are empty", Toast.LENGTH_SHORT).show()

                    }
                    is WallPaperUiState.Success -> {
                        binding.progressBar.visibility = View.GONE
//                        binding.imagesRecyclerView.adapter = ImagesRecyclerViewAdapter(wallpaperUiState.data)
                        // TODO update in recyclerView
                        populateDatainRecyclerView(wallpaperUiState.data)


                    }
                    is WallPaperUiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity , "Something went wrong", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }

    }
    private fun populateDatainRecyclerView(list :List<WallpaperLink>) {
        // update wallpaper adapter with list
        // update recycler view with the adapter
        val wallpaperadapter = ImagesRecyclerViewAdapter(list , ::onItemClick)
        binding.imagesRecyclerView.adapter = wallpaperadapter

    }
    private fun onItemClick(imageUrl: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = getBitmapFromUrl(imageUrl)
//            lifecycleScope.launch {
//                val bitmap = getBitmapFromUrl(imageUrl)
            setWallpaper(bitmap)
//            }
//            setWallpaper(bitmap)
        }
    }
    private suspend fun getBitmapFromUrl(url: String): Bitmap = withContext(Dispatchers.IO) {
        return@withContext try {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH).dontAnimate().dontTransform()
            Glide.with(this@MainActivity).asBitmap().load(url).apply(requestOptions).submit().get()
        } catch (e: Exception) {
            throw IOException("Failed to get bitmap from URL: $url", e)
        }
    }
    override fun setWallpaper(bitmap: Bitmap) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val wallpaperManager = WallpaperManager.getInstance(this@MainActivity)
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                    } else {
                        wallpaperManager.setBitmap(bitmap)
                    }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity, "Wallpaper set successfully", Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity, "Error setting wallpaper", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

