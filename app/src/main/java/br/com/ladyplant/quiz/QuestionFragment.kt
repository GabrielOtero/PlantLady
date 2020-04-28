package br.com.ladyplant.quiz

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import br.com.ladyplant.R
import br.com.ladyplant.components.toDp
import br.com.ladyplant.model.Question
import kotlinx.android.synthetic.main.layout_quiz_question.*

class QuestionFragment(
    private val questions: List<Question>,
    private val questionIdx: Int,
    var onOptionSelected: () -> Unit
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.layout_quiz_question, container, false
        ) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rebind()
    }

    private fun rebind() {
        question_title.text = questions[questionIdx].title
        buttons_container.removeAllViews()
        for (optIdx in questions[questionIdx].options.indices) {
            val btnTag = Button(context, null, R.attr.quizOptionButton)

            questions[questionIdx].answer?.let {
                if (optIdx == it) {
                    btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                }
            }

            if (questionIdx == questions.size - 1) {
                btnTag.setOnClickListener {
                    onOptionSelected()
                    questions[questionIdx].answer = optIdx
                    rebind()
                    btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                }
            } else {
                btnTag.setOnClickListener {
                    onOptionSelected()
                    questions[questionIdx].answer = optIdx
                    rebind()
                    btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                }
            }

            btnTag.setTypeface(null, Typeface.NORMAL)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(0, 24.toDp(), 0, 0)
            btnTag.setPadding(24.toDp(), 0, 24.toDp(), 0)
            btnTag.text = questions[questionIdx].options[optIdx]
            buttons_container.addView(btnTag, layoutParams)
        }
    }
}
