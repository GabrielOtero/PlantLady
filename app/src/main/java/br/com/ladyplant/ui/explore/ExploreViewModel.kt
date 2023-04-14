package br.com.ladyplant.ui.explore

import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByRoom
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByType
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlantsByType: GetPlantsByType,
    private val getPlantsByRoom: GetPlantsByRoom,
    override val viewState: ExploreViewState,
) : BaseViewModel<ExploreViewState, ExploreViewAction>() {

    override fun dispatchViewAction(viewAction: ExploreViewAction) {
        viewModelScope.launch {
            when (viewAction) {
                is ExploreViewAction.GetPlantByRoom -> {
                    callGetPlantsByRoom(viewAction)
                }
                is ExploreViewAction.GetPlantByType -> {
                    callGetPlantsByType(viewAction)
                }
            }
        }
    }

    private suspend fun callGetPlantsByRoom(viewAction: ExploreViewAction.GetPlantByRoom) {
        viewState.loading.postValue(true)
        when (val plantResult = getPlantsByRoom(idRoom = viewAction.idRoom)) {
            is DomainResult.Success -> {
                viewState.action.postValue(
                    ExploreViewState.Action.GoToResultList(
                        plantResult.data as ArrayList<Plant>
                    )
                )
                viewState.loading.postValue(false)
            }
            is DomainResult.Failure -> {
                viewState.loading.postValue(false)
            }
        }
    }

    private suspend fun callGetPlantsByType(viewAction: ExploreViewAction.GetPlantByType) {
        viewState.loading.postValue(true)
        when (val plantResult = getPlantsByType(idType = viewAction.idType)) {
            is DomainResult.Success -> {
                viewState.action.postValue(
                    ExploreViewState.Action.GoToResultList(
                        plantResult.data as ArrayList<Plant>
                    )
                )
                viewState.loading.postValue(false)
            }
            is DomainResult.Failure -> {
                viewState.loading.postValue(false)
            }
        }
    }

}
