package br.com.ladyplant.ui.result

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.ui.base.SingleLiveEvent

class ResultListViewState {

    val action: SingleLiveEvent<Action> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    sealed class Action {
        data class ShowByTypeResult(val list: List<Plant>) : Action()
        data class ShowError(val errorMsg: String) : Action()
    }
}