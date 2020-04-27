package br.com.ladyplant.quiz

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.R
import br.com.ladyplant.components.CarouselRecyclerView
import br.com.ladyplant.components.toDp
import br.com.ladyplant.model.Question


class QuizViewAdapter : RecyclerView.Adapter<QuizViewHolder>() {

    private lateinit var mRecyclerView: CarouselRecyclerView

    var questions: MutableList<Question> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView as CarouselRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_quiz_question, parent, false)
        return QuizViewHolder(view, parent)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.question.text = questions[position].title
        bindOptions(position, holder)
    }

    private fun bindOptions(position: Int, holder: QuizViewHolder) {
        holder.btnsContainer.removeAllViews()
        for (optIdx in questions[position].options.indices) {
            val btnTag = Button(holder.parent.context, null, R.attr.quizOptionButton)

            questions[position].answer?.let {
                if (optIdx == it) {
                    btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                }
            }

            btnTag.setOnClickListener {
                questions[position].answer = optIdx
                bindOptions(position, holder)
                btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                mRecyclerView.scrollNext()
            }
            if (position == questions.size - 1) {
                btnTag.setOnClickListener {
                    questions[position].answer = optIdx
                    bindOptions(position, holder)
                    btnTag.setBackgroundResource(R.drawable.quizz_option_selected_shape)
                    Log.d("QUIZZ", "END")
                }
            }

            btnTag.setTypeface(null, Typeface.NORMAL)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(0, 24.toDp(), 0, 0)
            btnTag.setPadding(24.toDp(), 0, 24.toDp(), 0)
            btnTag.text = questions[position].options[optIdx]
            holder.btnsContainer.addView(btnTag, layoutParams)
        }
    }
}

class QuizViewHolder(view: View, val parent: ViewGroup) : RecyclerView.ViewHolder(view) {
    var btnsContainer: LinearLayout = itemView.findViewById(R.id.buttons_container)
    var question: TextView = itemView.findViewById(R.id.question)
}
