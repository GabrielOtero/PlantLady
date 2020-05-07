package br.com.ladyplant.view.result.quiz

import android.os.Bundle
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.domain.model.ResultType
import br.com.ladyplant.view.result.BaseResultListActivity

class QuizResultListActivity : BaseResultListActivity() {

    override fun title() = getString(R.string.quiz_result_activity_title)
    override fun subTitle() = getString(R.string.quiz_result_activity_subtitle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setItems(
            mutableListOf(
                ItemResult(1),
                ItemResult(2),
                ItemResult(3),
                ItemResult(
                    0,
                    getString(R.string.or_take_the_quiz_again),
                    ResultType.TAKE_QUIZ_AGAIN
                )
            )
        )
    }
}
