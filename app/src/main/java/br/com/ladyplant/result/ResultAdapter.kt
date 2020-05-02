package br.com.ladyplant.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.R
import br.com.ladyplant.details.DetailActivity
import br.com.ladyplant.model.PlantResultWrapper
import br.com.ladyplant.model.ResultType
import br.com.ladyplant.quiz.QuizActivity


//TODO Context on Adapter?
class ResultAdapter(val context: Context) : RecyclerView.Adapter<ResultViewHolder>() {

    var results: List<PlantResultWrapper> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return when (viewType) {
            ResultType.PLANT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_result_card, parent, false)
                ResultViewHolder(view)
            }
            ResultType.TAKE_QUIZ_AGAIN.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_take_quiz_again_card, parent, false)
                ResultViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_result_card, parent, false)
                ResultViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return results[position].resultType.ordinal
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        when (results[position].resultType) {
            ResultType.PLANT -> {
                holder.cardContainer.setOnClickListener {
                    startActivity(context, Intent(this.context, DetailActivity::class.java), null)
                }
            }
            ResultType.TAKE_QUIZ_AGAIN -> {
                holder.cardContainer.setOnClickListener {
                    startActivity(context, Intent(this.context, QuizActivity::class.java), null)
                }
            }
        }

    }
}

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardContainer: ConstraintLayout = itemView.findViewById(R.id.card_container)

}
