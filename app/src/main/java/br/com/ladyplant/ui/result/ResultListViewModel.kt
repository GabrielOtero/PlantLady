package br.com.ladyplant.ui.result

import androidx.lifecycle.SavedStateHandle
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.ui.base.BaseViewModel
import br.com.ladyplant.navigation.navTypes.PlantList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    override val viewState: ResultListViewState,
) : BaseViewModel<ResultListViewState, ResultListViewAction>() {

    val plantList: List<Plant> = (checkNotNull(savedStateHandle["list"]) as PlantList).value

    override fun dispatchViewAction(viewAction: ResultListViewAction) {
    }
}
