package br.com.ladyplant.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.usecase.InitUseCase
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.domain.usecase.interfaces.Init
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val initUseCase: Init,
) : ViewModel() {
    init {
        viewModelScope.launch {
            initUseCase()
        }
    }
}
