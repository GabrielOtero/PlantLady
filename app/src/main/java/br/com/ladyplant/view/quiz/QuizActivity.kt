package br.com.ladyplant.view.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import br.com.ladyplant.R
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.Question
import br.com.ladyplant.view.components.toDp
import br.com.ladyplant.view.result.quiz.QuizResultListActivity
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initPageView()
        back_arrow.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        if (quiz_view.currentItem == 0) {
            super.onBackPressed()
        } else {
            quiz_view.goPrevious()
        }
    }

    private fun initPageView() {
        //MOCK
        val questions = mutableListOf(
            Question(
                "Let’s begin! What kind of climate are you in?",
                listOf("Tropical", "Cold", "Dry", "Mild")
            ),
            Question(
                "What are your gardening skills? Be honest, there's no judgment here.",
                listOf(
                    "I can't keep dirt alive",
                    "Somewhere close to good",
                    "I definetly have a green thumb"
                )
            ),
            Question(
                "What kind of light is there where you want to put your plant?",
                listOf(
                    "Direct sunlight",
                    "There's brightness but no direct sunlight",
                    "Only artificial lights or a little brightness from the sun"
                )
            ),
            Question(
                "What are you looking for in your plant?",
                listOf(
                    "Something colorful",
                    "Something with interesting leafs",
                    "I don’t really mind"
                )
            ),
            Question(
                "Are you thinking of a ground plant to put on a vase, or a hanging plant to stay high?",
                listOf(
                    "Ground plant",
                    "Hanging plant"
                )
            ),
            Question(
                "Are you looking for just a pretty plant or something with a purpose?",
                listOf(
                    "It's more about aesthetics",
                    "Something for cooking or with medicinal properties"
                )
            ),
            Question(
                "This one is very important. \n" +
                        "Do you have a small kid or pet that might eat the plant?",
                listOf(
                    "Yes, that’s a risk",
                    "Nope, it’s all good"
                )
            )
        )
        ////

        quiz_view.adapter =
            QuizViewAdapter(supportFragmentManager, onQuesionAnswered = { questionIdx ->
                if (questionIdx == questions.size - 1) {
                    val intent = Intent(this, QuizResultListActivity::class.java)
                    intent.putExtra(Constants.EXTRA_CLIMATE_ID, questions[0].answer)
                    intent.putExtra(Constants.EXTRA_GARDENCARE_ID, questions[1].answer)
                    intent.putExtra(Constants.EXTRA_LIGHT_ID, questions[2].answer)
                    intent.putExtra(Constants.EXTRA_APPEARANCE_ID, questions[3].answer)
                    intent.putExtra(Constants.EXTRA_INPLACE_ID, questions[4].answer)
                    intent.putExtra(Constants.EXTRA_PURPOSE_ID, questions[5].answer)
                    intent.putExtra(Constants.EXTRA_EATABLE_ID, questions[6].answer)
                    startActivity(intent)
                    finish()
                } else {
                    quiz_view.goNext()
                }
            })

        quiz_view.onPositionChange = { newPos ->
            question_count.text = getString(R.string.quiz_activity_question_count_param, newPos + 1)
            val param = LinearLayout.LayoutParams(
                0.toDp(),
                LinearLayout.LayoutParams.MATCH_PARENT,
                (12.5 * (newPos + 1)).toFloat()
            )
            current_progress.layoutParams = param
        }

        quiz_view.adapter?.questions = questions
    }
}
