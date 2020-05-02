package br.com.ladyplant.result

import br.com.ladyplant.R
import br.com.ladyplant.model.Constants
import br.com.ladyplant.model.Plant
import br.com.ladyplant.model.PlantResultWrapper
import br.com.ladyplant.model.ResultType

class ByTypeResultListActivity : BaseResultListActivity() {

    override fun title() = getString(R.string.explore_activity_title)
    override fun subTitle(): String {
        return getString(
            R.string.by_type_result_list_activity_subtitle,
            intent.extras?.getString(Constants.FILTER_NAME).toString()
        )
    }

    override fun items(): List<PlantResultWrapper> {
        return mutableListOf(
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant()),
            PlantResultWrapper(Plant(), ResultType.TAKE_QUIZ_AGAIN)
        )
    }
}
