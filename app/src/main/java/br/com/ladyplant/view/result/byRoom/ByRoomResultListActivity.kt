package br.com.ladyplant.view.result.byRoom

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.ladyplant.R
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.data.repository.Status
import br.com.ladyplant.data.repository.mapper.ItemResultMapper
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.model.ResultType
import br.com.ladyplant.view.result.BaseResultListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ByRoomResultListActivity : BaseResultListActivity() {

    private val viewModel by viewModel<ByRoomResultListViewModel>()

    override fun title() = getString(R.string.explore_activity_title)
    override fun subTitle(): String {
        return getString(
            R.string.by_room_result_list_activity_subtitle,
            intent.extras?.getString(Constants.EXTRA_FILTER_NAME).toString()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getInt(Constants.EXTRA_FILTER_ID)?.let { viewModel.onViewCreated(it) }
        viewModel.roomLV.observe(this, observer)
    }

    private val observer = Observer<Resource<List<Plant>>> {
        when (it.status) {
            Status.SUCCESS -> {
                val items = ItemResultMapper().transform(it.data).toMutableList()
                if (items.size > 0) items.add(
                    ItemResult(
                        0,
                        getString(R.string.or_take_the_quiz_to_find_your_plant),
                        resultType = ResultType.TAKE_QUIZ_AGAIN
                    )
                )
                setItems(items)
            }
            Status.ERROR -> showError(it.message)
            Status.LOADING -> showLoading()
        }
    }
}
