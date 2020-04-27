package br.com.ladyplant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterByTypeAdapter : RecyclerView.Adapter<ByTypeViewHolder>() {

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
                else -> holder.img.setImageResource(R.drawable.ic_filter_by_type_cactus)
            }
            holder.desc.text = description
        }
    }
}

class ByTypeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var desc: TextView = itemView.findViewById(R.id.desc)
    var img: ImageView = itemView.findViewById(R.id.img)
}
