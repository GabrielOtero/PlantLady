package br.com.ladyplant.view.result.quiz

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.ladyplant.R
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.data.repository.Status
import br.com.ladyplant.data.repository.mapper.ItemResultMapper
import br.com.ladyplant.domain.model.*
import br.com.ladyplant.view.result.BaseResultListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizResultListActivity : BaseResultListActivity() {

    private val viewModel by viewModel<QuizResultViewModel>()

    override fun title() = getString(R.string.quiz_result_activity_title)
    override fun subTitle() = getString(R.string.quiz_result_activity_subtitle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val climateId = intent.extras?.getInt(Constants.EXTRA_CLIMATE_ID) ?: 0
        val gardenCareId = intent.extras?.getInt(Constants.EXTRA_GARDENCARE_ID) ?: 0
        val appearanceId = intent.extras?.getInt(Constants.EXTRA_APPEARANCE_ID) ?: 0
        val lightId = intent.extras?.getInt(Constants.EXTRA_LIGHT_ID) ?: 0
        val inplaceId = intent.extras?.getInt(Constants.EXTRA_INPLACE_ID) ?: 0
        val purposeId = intent.extras?.getInt(Constants.EXTRA_PURPOSE_ID) ?: 0
        val eatableId = intent.extras?.getInt(Constants.EXTRA_EATABLE_ID) ?: 0

        viewModel.onViewCreated(climateId, gardenCareId, appearanceId, lightId, inplaceId, purposeId, eatableId)
        viewModel.quizResultLV.observe(this, observer)
    }

    private val observer = Observer<Resource<List<Plant>>> {
        when (it.status) {
            Status.SUCCESS -> {
                val items = ItemResultMapper().transform(it.data).toMutableList()
                if (items.size > 0) items.add(TakeQuizAgainResult(getString(R.string.or_take_the_quiz_again)))
                setItems(items)
            }
            Status.ERROR -> showError(it.message)
            Status.LOADING -> showLoading()
        }
    }
}
