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
        holder.btnsContainer.removeAllViews()
        for (element in questions[position].options) {
            val btnTag = Button(holder.parent.context, null, R.attr.quizOptionButton)

            btnTag.setOnClickListener { mRecyclerView.scrollNext() }
            if (position == questions.size - 1) {
                btnTag.setOnClickListener { Log.d("QUIZZ", "END") }
            }

            btnTag.setTypeface(null, Typeface.NORMAL)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(0, 24.toDp(), 0, 0)
            btnTag.text = element
            holder.btnsContainer.addView(btnTag, layoutParams)
        }
    }
}

class QuizViewHolder(view: View, val parent: ViewGroup) : RecyclerView.ViewHolder(view) {
    var btnsContainer: LinearLayout = itemView.findViewById(R.id.buttons_container)
    var question: TextView = itemView.findViewById(R.id.question)
}
