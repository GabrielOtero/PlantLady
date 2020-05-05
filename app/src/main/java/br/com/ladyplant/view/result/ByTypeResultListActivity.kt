package br.com.ladyplant.view.result

import br.com.ladyplant.R
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.domain.model.ResultType

class ByTypeResultListActivity : BaseResultListActivity() {

    override fun title() = getString(R.string.explore_activity_title)
    override fun subTitle(): String {
        return getString(
            R.string.by_type_result_list_activity_subtitle,
            intent.extras?.getString(Constants.EXTRA_FILTER_NAME).toString()
        )
    }

    override fun items(): List<ItemResult> {
        val description = getString(R.string.or_take_the_quiz_to_find_your_plant)
        return mutableListOf(
            ItemResult(),
            ItemResult(),
            ItemResult(),
            ItemResult(),
            ItemResult(),
            ItemResult(),
            ItemResult(description, ResultType.TAKE_QUIZ_AGAIN)
        )
    }
}
