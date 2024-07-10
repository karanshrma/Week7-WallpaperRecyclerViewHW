package com.example.wallpaperapp.data.api

import com.example.wallpaperapp.data.api.model.PicsumItem
import com.example.wallpaperapp.domain.entity.WallpaperLink
import com.example.wallpaperapp.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(private val picsumapi: PicSumApi) : WallpaperRepository {
    override fun getWallpapers(): Flow<List<WallpaperLink>>  = flow {
        //api.getwallpapers()
        val response = picsumapi.getWallpaperImages()
        response?.let {
            val wallpaperLinks : List<WallpaperLink> = response.map {
                WallpaperLink(it.downloadUrl.toString())
            }
            emit(wallpaperLinks)
        }

        //Big List into Small List
//        if (response!=null){
//            for (item in response){
//                //val wallpaperLinks : MutableList<WallpaperLink>
//                val wallpaperLink = item.downloadUrl?.let { WallpaperLink(it) }
//                wallpaperLink?.let { wallpaperLinks.add(it) }
//            }
//        }







}}