package com.example.wallpaperapp.di

import com.example.wallpaperapp.Utils.Constants.BASE_URL
import com.example.wallpaperapp.data.api.PicSumApi
import dagger.Provides
import retrofit2.Retrofit

class AppModule {

    @Provides
    fun provideRetrofitApi(
        // Potential dependencies of this type
    ): PicSumApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(PicSumApi::class.java)
    }
}