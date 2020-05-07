package br.com.ladyplant.view.result

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val description = getString(R.string.or_take_the_quiz_to_find_your_plant)
        setItems(
            mutableListOf(
                ItemResult(),
                ItemResult(),
                ItemResult(),
                ItemResult(),
                ItemResult(),
                ItemResult(),
                ItemResult(0, description, ResultType.TAKE_QUIZ_AGAIN)
            )
        )
    }
}
