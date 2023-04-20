package br.com.ladyplant.ui.plantDetail

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.ui.base.BaseViewModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val getPlantById: GetPlantById,
    savedStateHandle: SavedStateHandle,
    override val viewState: PlantDetailViewState,
) : BaseViewModel<PlantDetailViewState, PlantDetailViewAction>() {

    private val plantId: Int = checkNotNull(savedStateHandle["plantId"])

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
                logPlantDetail(plantResult.data)
                viewState.action.postValue(
                    PlantDetailViewState.Action.ShowResult(plantResult.data)
                )
                viewState.loading.postValue(false)
            }
            is DomainResult.Failure -> {
                viewState.loading.postValue(false)
                viewState.action.postValue(
                    PlantDetailViewState.Action.ShowError("")
                )
            }
        }
    }

    private fun logPlantDetail(plant: Plant) {
        val bundle = Bundle().apply {
            putString("name", plant.name)
        }
        Firebase.analytics.logEvent("plant_detail_view", bundle)
    }
}