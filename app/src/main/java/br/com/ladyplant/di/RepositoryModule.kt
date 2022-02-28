package br.com.ladyplant.di

import br.com.ladyplant.repository.PlantRepository
import br.com.ladyplant.repository.PlantRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: PlantRepositoryImpl): PlantRepository
}