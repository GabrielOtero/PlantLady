package br.com.ladyplant.ui.explore

import androidx.lifecycle.SavedStateHandle
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByType
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ExploreViewModel(
    private val getPlantById: GetPlantsByType,
    savedStateHandle: SavedStateHandle,
    override val viewState: ExploreViewState,
) : BaseViewModel<ExploreViewState, ExploreViewAction>() {

    init {

    }
    override fun dispatchViewAction(viewAction: ExploreViewAction) {
        TODO("Not yet implemented")
    }

}
