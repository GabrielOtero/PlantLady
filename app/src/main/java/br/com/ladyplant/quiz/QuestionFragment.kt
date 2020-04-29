package br.com.ladyplant.quiz

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import br.com.ladyplant.R
import br.com.ladyplant.components.toDp
import br.com.ladyplant.model.Question
import io.github.inflationx.calligraphy3.CalligraphyUtils
import kotlinx.android.synthetic.main.layout_quiz_question.*

class QuestionFragment(
    private val question: Question,
    var onOptionSelected: ((optIdx: Int) -> Unit)? = null
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
        bind()
    }

    private fun bind() {
        question_title.text = question.title
        buttons_container.removeAllViews()
        for (optIdx in question.options.indices) {
            val btnOpt = Button(context, null, R.attr.quizOptionButton)

            question.answer?.let {
                if (it == optIdx) {
                    btnOpt.setBackgroundResource(R.drawable.quiz_option_selected_shape)
                }
            }

            btnOpt.setTypeface(null, Typeface.NORMAL)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            btnOpt.setOnClickListener {
                onOptionSelected?.let { it(optIdx) }
                setSelectedOpt(btnOpt)
            }
            CalligraphyUtils.applyFontToTextView(
                btnOpt,
                Typeface.createFromAsset(context?.assets, "fonts/quicksandvariablefont_wght.ttf")
            )
            layoutParams.setMargins(0, 24.toDp(), 0, 0)
            btnOpt.setPadding(24.toDp(), 0, 24.toDp(), 0)
            btnOpt.text = question.options[optIdx]
            buttons_container.addView(btnOpt, layoutParams)
        }
    }


    private fun setSelectedOpt(btn: Button) {
        buttons_container?.let {
            for (child in it.children) {
                child.setBackgroundResource(R.drawable.quiz_option_shape)
                child.setPadding(24.toDp(), 0, 24.toDp(), 0)
            }

            btn.setBackgroundResource(R.drawable.quiz_option_selected_shape)
            btn.setPadding(24.toDp(), 0, 24.toDp(), 0)
        }

    }
}
