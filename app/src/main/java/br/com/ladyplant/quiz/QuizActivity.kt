package br.com.ladyplant.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.ladyplant.R
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initRecyclerView()
        question_count.text = getString(R.string.quiz_activity_question_count, 1)
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
    }
}
