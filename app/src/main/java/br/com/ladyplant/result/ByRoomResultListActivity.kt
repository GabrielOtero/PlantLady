package br.com.ladyplant.result

import br.com.ladyplant.R
import br.com.ladyplant.model.Constants
import br.com.ladyplant.model.ItemResult

class ByRoomResultListActivity : BaseResultListActivity() {

    override fun title() = getString(R.string.explore_activity_title)
    override fun subTitle(): String {
        return getString(
            R.string.by_room_result_list_activity_subtitle,
            intent.extras?.getString(Constants.EXTRA_FILTER_NAME).toString()
        )
    }

    override fun items(): List<ItemResult> {
        return mutableListOf()
    }
}
