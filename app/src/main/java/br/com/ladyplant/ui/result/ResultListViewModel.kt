package br.com.ladyplant.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ladyplant.repository.PlantRepository
import br.com.ladyplant.repository.PlantRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(private val repository: PlantRepository) : ViewModel() {
    init{
        viewModelScope.launch(Dispatchers.Main){
            repository.getPlantsByType(1).collect { response ->
                if(response.isSuccessful){
                    println(response.body())
                } else {
                    println("erro")
                    println(response.message())
                }
            }
        }
    }
}