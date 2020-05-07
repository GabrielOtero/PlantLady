package br.com.ladyplant.view.result.byRoom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.domain.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class ByRoomResultListViewModel(private val repository: PlantRepository) : BaseViewModel() {
    private val room = MutableLiveData<Int>()

    fun onViewCreated(roomId: Int) {
        room.value = roomId
    }

    var roomLV = room.switchMap { idRoom ->
        liveData(coroutineContext) {
            emit(Resource.loading(null))
            emit(repository.getPlantsByRoom(idRoom))
        }
    }
}
