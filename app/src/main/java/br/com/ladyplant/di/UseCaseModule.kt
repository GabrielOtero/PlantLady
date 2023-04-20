package br.com.ladyplant.di

import br.com.ladyplant.domain.mapper.DataErrorResultToDomainErrorResultMapper
import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.usecase.GetPlantByIdUseCase
import br.com.ladyplant.domain.usecase.GetPlantsByRoomUseCase
import br.com.ladyplant.domain.usecase.GetPlantsByTypeUseCase
import br.com.ladyplant.domain.usecase.GetQuizResultUseCase
import br.com.ladyplant.domain.usecase.InitUseCase
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByRoom
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByType
import br.com.ladyplant.domain.usecase.interfaces.GetQuizResult
import br.com.ladyplant.domain.usecase.interfaces.Init
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetPlantsByType(
        impl: GetPlantsByTypeUseCase
    ): GetPlantsByType

    @Binds
    abstract fun bindGetPlantsByRoom(
        impl: GetPlantsByRoomUseCase
    ): GetPlantsByRoom

    @Binds
    abstract fun bindGetPlantByIdUseCase(
        impl: GetPlantByIdUseCase
    ): GetPlantById

    @Binds
    abstract fun bindGetQuizResultUseCase(
        impl: GetQuizResultUseCase
    ): GetQuizResult

    @Binds
    abstract fun bindInitUseCase(
        impl: InitUseCase
    ): Init
}

@Module
@InstallIn(ViewModelComponent::class)
class MapperModule {

    @Provides
    fun bindPlantDtoToPlantMapper(): PlantDtoToPlantMapper {
        return PlantDtoToPlantMapper()
    }

    @Provides
    fun bindDataErrorResultToDomainErrorResultMapper(): DataErrorResultToDomainErrorResultMapper {
        return DataErrorResultToDomainErrorResultMapper()
    }
}
