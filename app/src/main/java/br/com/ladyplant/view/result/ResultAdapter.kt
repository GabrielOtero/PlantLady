package br.com.ladyplant.view.result

import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.BuildConfig
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.HeaderResult
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.domain.model.ResultType
import br.com.ladyplant.domain.model.TakeQuizAgainResult

class ResultAdapter(
    private val loadImageListener: LoadImageListener,
    private val takeQuizAgainClickListener: TakeQuizAgainClickListener,
    private val itemResultClickListener: ItemResultClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                TakeQuizAgainViewHolder(view)
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
        val itemResult = results[position]
        when (itemResult.resultType) {
            ResultType.HEADER -> {
                (holder as HeaderViewHolder).bind(itemResult as HeaderResult)
            }
            ResultType.PLANT -> {
                (holder as ResultViewHolder).bind(
                    itemResult,
                    itemResultClickListener,
                    loadImageListener
                )
            }
            ResultType.TAKE_QUIZ_AGAIN -> {
                (holder as TakeQuizAgainViewHolder).bind(
                    (itemResult as TakeQuizAgainResult),
                    takeQuizAgainClickListener
                )
            }
        }
    }
}

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardContainer: ConstraintLayout = itemView.findViewById(R.id.card_container)
    var descripton: TextView = itemView.findViewById(R.id.description)
    var image: ImageView = itemView.findViewById(R.id.plant_image)

    fun bind(
        itemResult: ItemResult,
        itemResultClickListener: ItemResultClickListener,
        loadImageListener: LoadImageListener
    ) {
        descripton.text = itemResult.description
        cardContainer.setOnClickListener {
            itemResultClickListener.onClick(itemResult.id)
        }
        itemResult.image?.let {
            loadImageListener.onLoad(
                Uri.parse(BuildConfig.IMAGES_END_POINT + itemResult.image),
                this.image
            )
        }
    }
}

class TakeQuizAgainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardContainer: ConstraintLayout = itemView.findViewById(R.id.card_container)
    var descripton: TextView = itemView.findViewById(R.id.description)

    fun bind(
        itemResult: TakeQuizAgainResult,
        takeQuizAgainClickListener: TakeQuizAgainClickListener
    ) {
        cardContainer.setOnClickListener {
            takeQuizAgainClickListener.onClick()
        }
        val content = SpannableString(itemResult.title)
        content.setSpan(UnderlineSpan(), 0, itemResult.title?.length ?: 0, 0)
        descripton.text = content
    }
}


class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.result_title)
    var subtitle: TextView = itemView.findViewById(R.id.result_subtitle)

    fun bind(itemResult: HeaderResult) {
        title.text = itemResult.title
        subtitle.text = itemResult.subtitle
    }
}