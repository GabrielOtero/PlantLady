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

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
        TODO("Not yet implemented")
    }
}
