package com.example.wallpaperapp.data.api

import com.example.wallpaperapp.Utils.Resource
import com.example.wallpaperapp.data.api.model.PicsumItem
import com.example.wallpaperapp.domain.entity.WallpaperLink
import com.example.wallpaperapp.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class WallpaperRepositoryImpl @Inject constructor(private val picsumapi: PicSumApi) : WallpaperRepository {
    override fun getWallpapers(): Flow<Resource<List<WallpaperLink>>> = flow {
        try {
            val response = picsumapi.getWallpaperImages()
            response?.let {
                val wallpaperLinks: List<WallpaperLink> = it.map { item ->
                    WallpaperLink(item.downloadUrl.toString())
                }
                emitAll(flowOf(Resource.Success(wallpaperLinks)))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(null, e.message ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error(null, e.message ?: "Network connection Error"))
        }
    }
}
