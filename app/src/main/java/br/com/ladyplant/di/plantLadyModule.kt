package br.com.ladyplant.di

import br.com.ladyplant.BuildConfig.API_END_POINT
import br.com.ladyplant.details.DetailViewModel
import br.com.ladyplant.repository.PlantLadyApi
import br.com.ladyplant.repository.PlantRepository
import br.com.ladyplant.repository.PlantRepositoryImpl
import br.com.ladyplant.repository.ResponseHandler
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_RETROFIT = "KOIN_RETROFIT"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val plantLadyModule = module {

    viewModel {
        DetailViewModel(get<PlantRepository>())
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>(named(KOIN_RETROFIT)).create(PlantLadyApi::class.java)
    }

    factory {
        PlantRepositoryImpl(get<PlantLadyApi>(), get<ResponseHandler>()) as PlantRepository
    }

    factory {
        ResponseHandler()
    }
}