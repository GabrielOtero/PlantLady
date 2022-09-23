package br.com.ladyplant.ui.result

import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByType
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    private val getPlantsByType: GetPlantsByType,
    override val viewState: ResultListViewState,
) : BaseViewModel<ResultListViewState, ResultListViewAction>() {

    init {
        viewState.loading.postValue(true)

        viewModelScope.launch {
            when (val plantResult = getPlantsByType(idType = 1)) {
                is DomainResult.Success -> {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowByTypeResult(plantResult.data)
                    )
                }
                is DomainResult.Failure -> {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowError(plantResult.errorResult.message)
                    )
                }
            }
        }
    }

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
        TODO("Not yet implemented")
    }
}
