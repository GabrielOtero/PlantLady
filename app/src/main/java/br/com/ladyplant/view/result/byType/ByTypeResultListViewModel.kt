package br.com.ladyplant.view.result.byType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.Resource
import kotlinx.coroutines.Dispatchers

class ByTypeResultListViewModel(private val repository: PlantRepository) : ViewModel() {
    private val type = MutableLiveData<Int>()

    fun onViewCreated(roomId: Int) {
        type.value = roomId
    }

    var typeLV = type.switchMap { idType ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.getPlantsByType(idType))
        }
    }
}
