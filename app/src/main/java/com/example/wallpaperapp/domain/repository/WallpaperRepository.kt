package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.data.api.PicSumApi
import com.example.wallpaperapp.data.api.model.PicsumItem
import com.example.wallpaperapp.domain.entity.WallpaperLink
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    fun getWallpapers() : Flow<List<WallpaperLink>>




}