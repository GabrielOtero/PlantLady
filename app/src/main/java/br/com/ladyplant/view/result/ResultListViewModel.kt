package br.com.ladyplant.view.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.Resource
import kotlinx.coroutines.Dispatchers

class ResultListViewModel(private val repository: PlantRepository) : ViewModel() {
    private val room = MutableLiveData<Int>()

    fun onViewCreated(roomId: Int) {
        room.value = roomId
    }

    var roomLV = room.switchMap { idRoom ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.getPlantsByRoom(idRoom))
        }
    }
}
