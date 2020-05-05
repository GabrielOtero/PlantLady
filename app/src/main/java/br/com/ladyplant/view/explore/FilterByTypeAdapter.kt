package br.com.ladyplant.view.explore

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.ByTypeFilter
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.PlantType
import br.com.ladyplant.view.result.ByTypeResultListActivity

class FilterByTypeAdapter(val context: Context) : RecyclerView.Adapter<ByTypeViewHolder>() {

    var types: MutableList<ByTypeFilter> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByTypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_by_type_filter, parent, false)
        return ByTypeViewHolder(view)
    }

    override fun getItemCount() = types.size

    override fun onBindViewHolder(holder: ByTypeViewHolder, position: Int) {
        types[position].apply {
            when (type) {
                PlantType.CACTUS -> holder.img.setImageResource(R.drawable.ic_filter_by_type_cactus)
                PlantType.LIANAS -> holder.img.setImageResource(R.drawable.ic_filter_by_type_lianas)
                PlantType.PALMS -> holder.img.setImageResource(R.drawable.ic_filter_by_type_palms)
                PlantType.FRUITS -> holder.img.setImageResource(R.drawable.ic_filter_by_type_fruits)
            }
            holder.desc.text = description
        }

        holder.card.setOnClickListener {
            val intent = Intent(this.context, ByTypeResultListActivity::class.java)
            intent.putExtra(Constants.EXTRA_FILTER_NAME, types[position].description)
            ContextCompat.startActivity(context, intent, null)
        }
    }
}

class ByTypeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var desc: TextView = itemView.findViewById(R.id.desc)
    var img: ImageView = itemView.findViewById(R.id.img)
    var card: ConstraintLayout = itemView.findViewById(R.id.filter_card)
}
