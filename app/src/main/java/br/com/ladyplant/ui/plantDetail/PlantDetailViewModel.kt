package br.com.ladyplant.ui.plantDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val getPlantById: GetPlantById,
    savedStateHandle: SavedStateHandle,
    override val viewState: PlantDetailViewState,
) : BaseViewModel<PlantDetailViewState, PlantDetailViewAction>() {

    private val plantId: Int = checkNotNull(savedStateHandle["plantId"])
    private val _plant = MutableLiveData<Plant>()
    val plant: LiveData<Plant> = _plant

    init {
        viewModelScope.launch {
            callGetPlantById(plantId)
        }
    }

    override fun dispatchViewAction(viewAction: PlantDetailViewAction) {
        viewModelScope.launch {
            when (viewAction) {
                is PlantDetailViewAction.Refresh -> {
                    callGetPlantById(plantId)
                }
            }
        }
    }

    private suspend fun callGetPlantById(plantId: Int) {
        viewState.loading.postValue(true)

        when (val plantResult = getPlantById(idPlant = plantId)) {
            is DomainResult.Success -> {
                _plant.value = plantResult.data
                viewState.loading.postValue(false)
            }
            is DomainResult.Failure -> {

                viewState.loading.postValue(false)
            }
        }
    }
}