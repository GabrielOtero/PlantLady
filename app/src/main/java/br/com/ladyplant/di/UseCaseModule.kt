package br.com.ladyplant.di

import br.com.ladyplant.domain.usecase.GetPlantsByType
import br.com.ladyplant.domain.usecase.GetPlantsByTypeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindRepository(impl: GetPlantsByTypeUseCase): GetPlantsByType

}