package br.com.ladyplant.di

import br.com.ladyplant.repository.PlantLadyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providePlantLadyApi(): PlantLadyApi = Retrofit.Builder()
        .baseUrl("https://yardman-prod.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .build().create(PlantLadyApi::class.java)
}