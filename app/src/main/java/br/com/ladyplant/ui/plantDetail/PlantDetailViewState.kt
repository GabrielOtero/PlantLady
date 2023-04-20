package br.com.ladyplant.ui.plantDetail

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.ui.base.SingleLiveEvent
import kotlinx.coroutines.flow.StateFlow

class PlantDetailViewState {

    val action: SingleLiveEvent<Action> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    sealed class Action {
        data class ShowResult(val plant: Plant) : Action()
        data class ShowError(val errorMsg: String) : Action()
    }
}
