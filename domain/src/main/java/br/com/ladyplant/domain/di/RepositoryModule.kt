package br.com.ladyplant.domain.di

import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.plant.PlantRepositoryImpl
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