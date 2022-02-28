package br.com.ladyplant.ui.result

import androidx.lifecycle.viewModelScope
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    private val repository: PlantRepository,
    override val viewState: ResultListViewState = ResultListViewState()
) : BaseViewModel<ResultListViewState, ResultListViewAction>() {

    init {
        viewState.loading.postValue(true)

        viewModelScope.launch(Dispatchers.Main) {
            repository.getPlantsByType(1).collect { response ->
                viewState.loading.postValue(false)

                if (response.isSuccessful) {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowByTypeResult(response.body()!!)
                    )
                } else {
                    viewState.action.postValue(
                        ResultListViewState.Action.ShowError(response.message())
                    )
                }

            }
        }
    }

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
        TODO("Not yet implemented")
    }
}