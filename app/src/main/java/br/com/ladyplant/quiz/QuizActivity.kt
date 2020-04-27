package br.com.ladyplant.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.ladyplant.R
import br.com.ladyplant.model.Question
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initRecyclerView()
        back_arrow.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        if (quiz_view.position == 0) {
            super.onBackPressed()
        } else {
            quiz_view.scrollPrevious()
        }
    }

    private fun initRecyclerView() {
        var byTypeAdapter = QuizViewAdapter()
        quiz_view.adapter = byTypeAdapter
        quiz_view.onPositionChangeCallback {
            question_count.text = getString(R.string.quiz_activity_question_count_param, it + 1)
        }

        //MOCK ?
        byTypeAdapter.questions = mutableListOf(
            Question(
                "Let’s begin! What kind of climate are you in?",
                listOf("Tropical", "Cold", "Dry", "Mild")
            ),
            Question(
                "What are your gardening skills? Be honest, there's no judgment here.",
                listOf(
                    "I cant't keep a dirty alive",
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
    }
}
