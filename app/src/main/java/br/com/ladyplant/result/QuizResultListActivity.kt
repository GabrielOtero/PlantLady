package br.com.ladyplant.result

import br.com.ladyplant.R
import br.com.ladyplant.model.Plant
import br.com.ladyplant.model.ItemResult
import br.com.ladyplant.model.ResultType

class QuizResultListActivity : BaseResultListActivity() {

    override fun title() = getString(R.string.quiz_result_activity_title)
    override fun subTitle() = getString(R.string.quiz_result_activity_subtitle)

    override fun items(): List<ItemResult> {
        return mutableListOf(
            ItemResult(),
            ItemResult(),
            ItemResult(),
            ItemResult(getString(R.string.or_take_the_quiz_again), ResultType.TAKE_QUIZ_AGAIN)
        )
    }
}
