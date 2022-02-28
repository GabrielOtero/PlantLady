package br.com.ladyplant.di

import br.com.ladyplant.repository.PlantLadyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import br.com.ladyplant.BuildConfig.API_END_POINT
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePlantLadyApi(): PlantLadyApi = Retrofit.Builder()
        .baseUrl(API_END_POINT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .build().create(PlantLadyApi::class.java)
}