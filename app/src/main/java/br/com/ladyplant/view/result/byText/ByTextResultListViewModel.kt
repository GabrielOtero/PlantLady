package br.com.ladyplant.view.result.byText

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.domain.base.BaseViewModel

class ByTextResultListViewModel(private val repository: PlantRepository) : BaseViewModel() {
    private val txt = MutableLiveData<String>()

    fun onViewCreated(text: String) {
        txt.value = text
    }

    var textLV = txt.switchMap { text ->
        liveData(coroutineContext) {
            emit(Resource.loading(null))
            emit(repository.getPlantsByText(text.trim()))
        }
    }
}
