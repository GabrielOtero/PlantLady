package br.com.ladyplant.view.result

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.R
import br.com.ladyplant.view.details.DetailActivity
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.HeaderResult
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.domain.model.ResultType
import br.com.ladyplant.view.quiz.QuizActivity


//TODO Context on Adapter?
class ResultAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var results: List<ItemResult> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ResultType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.result_header, parent, false)
                HeaderViewHolder(view)
            }
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (results[position].resultType) {
            ResultType.HEADER -> {
                val header = results[position] as HeaderResult
                (holder as HeaderViewHolder).title.text = header.title
                holder.subtitle.text = header.subtitle
            }
            ResultType.PLANT -> {
                (holder as ResultViewHolder).cardContainer.setOnClickListener {
                    val intent = Intent(this.context, DetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_PLANT_ID, 1)
                    startActivity(context, intent, null)
                }
            }
            ResultType.TAKE_QUIZ_AGAIN -> {
                (holder as ResultViewHolder).cardContainer.setOnClickListener {
                    startActivity(context, Intent(this.context, QuizActivity::class.java), null)
                }
                val content = SpannableString(results[position].description)
                content.setSpan(UnderlineSpan(), 0, results[position].description?.length ?: 0, 0)
                holder.descripton.text = content
            }
        }

    }
}

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardContainer: ConstraintLayout = itemView.findViewById(R.id.card_container)
    var descripton: TextView = itemView.findViewById(R.id.description)
}


class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.result_title)
    var subtitle: TextView = itemView.findViewById(R.id.result_subtitle)
}