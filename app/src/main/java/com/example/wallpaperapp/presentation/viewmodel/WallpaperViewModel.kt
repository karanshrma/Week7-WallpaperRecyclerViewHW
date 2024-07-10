package com.example.wallpaperapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.entity.WallpaperLink
import com.example.wallpaperapp.presentation.WallPaperUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class WallPaperViewModel : ViewModel() {

    private val _wallpaperList: MutableStateFlow<WallPaperUiState> =
        MutableStateFlow(WallPaperUiState.Loading)
    val wallpaperList get() = _wallpaperList.asStateFlow()

    fun fetchWallPapers() {
        viewModelScope.launch {
//            try {
//                // Simulate network call to fetch wallpapers
//                //val wallpapers = getWallpapersFromNetwork()
//                _wallpaperList.value = WallPaperUiState.Success(wallpapers)
//            } catch (e: Exception) {
//                _wallpaperList.value = WallPaperUiState.Error("Failed to fetch wallpapers")
//            }
        }
    }
}