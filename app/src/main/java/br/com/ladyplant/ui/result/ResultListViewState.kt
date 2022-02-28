package br.com.ladyplant.ui.result

import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.ui.base.SingleLiveEvent

class ResultListViewState {

    val action: SingleLiveEvent<Action> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    sealed class Action{
        data class ShowByTypeResult(val list: List<PlantDto>) : Action()
        data class ShowError(val errorMsg: String) : Action()
    }
}