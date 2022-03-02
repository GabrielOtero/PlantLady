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
            getPlantsByType(1).handleResult(
                onSuccess = {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowByTypeResult(it)
                    )
                },
                onError = {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowError(it.exceptionMessage.orEmpty())
                    )
                },
                onFinish = {
                    viewState.loading.postValue(false)
                }
            )
        }

    }

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
        TODO("Not yet implemented")
    }
}