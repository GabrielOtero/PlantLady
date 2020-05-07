package br.com.ladyplant.view.result.byText

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.ladyplant.R
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.data.repository.Status
import br.com.ladyplant.data.repository.mapper.ItemResultMapper
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.view.result.BaseResultListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ByTextResultListActivity : BaseResultListActivity() {

    private val viewModel by viewModel<ByTextResultListViewModel>()

    override fun title() = getString(R.string.explore_activity_title)
    override fun subTitle(): String {
        return getString(
            R.string.by_text_result_list_activity_subtitle,
            intent.extras?.getString(Constants.EXTRA_FILTER_TEXT).toString()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getString(Constants.EXTRA_FILTER_TEXT)?.let { viewModel.onViewCreated(it) }
        viewModel.textLV.observe(this, observer)
    }

    private val observer = Observer<Resource<List<Plant>>> {
        when (it.status) {
            Status.SUCCESS -> {
                val items = ItemResultMapper().transform(it.data).toMutableList()
                setItems(items)
            }
            Status.ERROR -> showError(it.message)
            Status.LOADING -> showLoading()
        }
    }
}
