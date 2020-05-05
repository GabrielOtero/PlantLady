package br.com.ladyplant.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.BaseViewModel
import br.com.ladyplant.repository.PlantRepository
import br.com.ladyplant.repository.Resource
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val repository: PlantRepository) : BaseViewModel() {

    private val plant = MutableLiveData<Int>()

    fun onViewCreated(plantId: Int) {
        plant.value = plantId
    }

    var plantLV = plant.switchMap { id ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.getPlantById(id))
        }
    }
}