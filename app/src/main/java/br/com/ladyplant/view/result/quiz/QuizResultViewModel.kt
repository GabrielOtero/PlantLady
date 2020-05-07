package br.com.ladyplant.view.result.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.ladyplant.data.repository.PlantRepository
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.domain.base.BaseViewModel
import br.com.ladyplant.domain.model.Constants

class QuizResultViewModel(private val repository: PlantRepository) : BaseViewModel() {
    private val answersId = MutableLiveData<Map<String, Int>>()

    fun onViewCreated(
        climateId: Int,
        gardenCareId: Int,
        appearanceId: Int,
        lightId: Int,
        inplaceId: Int,
        purposeId: Int,
        eatableId: Int
    ) {
        answersId.value = mapOf(
            Constants.EXTRA_CLIMATE_ID to climateId,
            Constants.EXTRA_GARDENCARE_ID to gardenCareId,
            Constants.EXTRA_APPEARANCE_ID to appearanceId,
            Constants.EXTRA_LIGHT_ID to lightId,
            Constants.EXTRA_INPLACE_ID to inplaceId,
            Constants.EXTRA_PURPOSE_ID to purposeId,
            Constants.EXTRA_EATABLE_ID to eatableId

        )
    }

    var quizResultLV = answersId.switchMap { answers ->
        liveData(coroutineContext) {
            emit(Resource.loading(null))
            emit(
                repository.getQuizResult(
                    answers.getValue(Constants.EXTRA_CLIMATE_ID),
                    answers.getValue(Constants.EXTRA_GARDENCARE_ID),
                    answers.getValue(Constants.EXTRA_APPEARANCE_ID),
                    answers.getValue(Constants.EXTRA_LIGHT_ID),
                    answers.getValue(Constants.EXTRA_INPLACE_ID),
                    answers.getValue(Constants.EXTRA_PURPOSE_ID),
                    answers.getValue(Constants.EXTRA_EATABLE_ID)
                )
            )
        }
    }
}
