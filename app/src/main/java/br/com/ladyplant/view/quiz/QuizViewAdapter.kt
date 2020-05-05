package br.com.ladyplant.view.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.ladyplant.domain.model.Question


class QuizViewAdapter(
    fm: FragmentManager?,
    private val onQuesionAnswered: (questionIdx: Int) -> Unit
) : FragmentStatePagerAdapter(fm) {

    var questions: MutableList<Question> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment {
        val question = questions[position]

        return QuestionFragment(question, onOptionSelected = { optIdx ->
            onQuesionAnswered(position)
            question.answer = optIdx
        })
    }

    override fun getCount(): Int {
        return questions.size
    }

}

