package br.com.ladyplant.domain.di

import br.com.ladyplant.BuildConfig
import br.com.ladyplant.BuildConfig.API_END_POINT
import br.com.ladyplant.data.repository.PlantLadyApi
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.PlantRepositoryImpl
import br.com.ladyplant.data.repository.ResponseHandler
import br.com.ladyplant.view.details.DetailViewModel
import br.com.ladyplant.view.result.byRoom.ByRoomResultListViewModel
import br.com.ladyplant.view.result.byType.ByTypeResultListViewModel
import br.com.ladyplant.view.result.quiz.QuizResultViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    viewModel {
        ByRoomResultListViewModel(get<PlantRepository>())
    }

    viewModel {
        QuizResultViewModel(get<PlantRepository>())
    }

    viewModel {
        ByTypeResultListViewModel(get<PlantRepository>())
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
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