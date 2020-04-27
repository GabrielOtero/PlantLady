package br.com.ladyplant.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.ByTypeFilter
import br.com.ladyplant.R
import br.com.ladyplant.components.CarouselRecyclerView


class QuizViewAdapter : RecyclerView.Adapter<QuizViewHolder>() {
    private lateinit var mRecyclerView: CarouselRecyclerView

    var questions: MutableList<ByTypeFilter> = mutableListOf()
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
            .inflate(R.layout.quiz_question_layout, parent, false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8//questions.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.btn.setOnClickListener {
            mRecyclerView.scrollNext()
        }
    }
}

class QuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var btn: Button = itemView.findViewById(R.id.btn1)
}
