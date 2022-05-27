package br.com.ladyplant.ui.result

import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.usecase.GetPlantsByType
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
            getPlantsByType(idType = 1,
                onSuccessCallback = {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowByTypeResult(it)
                    )
                },
                onErrorCallback = { errorMsg ->
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowError(errorMsg)
                    )
                },
                onFinishCallback = {
                    viewState.loading.postValue(false)
                }
            )
        }

    }

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
        TODO("Not yet implemented")
    }
}
