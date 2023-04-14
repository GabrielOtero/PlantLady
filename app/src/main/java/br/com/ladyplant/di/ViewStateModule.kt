package br.com.ladyplant.di

import br.com.ladyplant.ui.explore.ExploreViewState
import br.com.ladyplant.ui.plantDetail.PlantDetailViewState
import br.com.ladyplant.ui.quizz.QuizViewState
import br.com.ladyplant.ui.result.ResultListViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewStateModule {

    @Provides
    fun bindResultListViewState(): ResultListViewState {
        return ResultListViewState()
    }

    @Provides
    fun bindPlantDetailViewState(): PlantDetailViewState {
        return PlantDetailViewState()
    }

    @Provides
    fun bindQuizViewState(): QuizViewState {
        return QuizViewState()
    }

    @Provides
    fun bindExploreViewState(): ExploreViewState {
        return ExploreViewState()
    }
}
