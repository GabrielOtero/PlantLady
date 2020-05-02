package br.com.ladyplant.explore

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
import br.com.ladyplant.model.ByRoomFilter
import br.com.ladyplant.model.Constants
import br.com.ladyplant.model.RoomType
import br.com.ladyplant.result.ByRoomResultListActivity

class FilterByRoomAdapter(val context: Context) : RecyclerView.Adapter<ByRoomViewHolder>() {

    var rooms: MutableList<ByRoomFilter> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByRoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_by_room_filter, parent, false)
        return ByRoomViewHolder(view)
    }

    override fun getItemCount() = rooms.size

    override fun onBindViewHolder(holder: ByRoomViewHolder, position: Int) {
        rooms[position].apply {
            when (type) {
                RoomType.LIVING -> holder.img.setImageResource(R.drawable.ic_filter_by_room_livingroom)
                RoomType.BED -> holder.img.setImageResource(R.drawable.ic_filter_by_room_bedroom)
                RoomType.DINNING -> holder.img.setImageResource(R.drawable.ic_filter_by_room_diningroom)
                RoomType.BATH -> holder.img.setImageResource(R.drawable.ic_filter_by_room_bathroom)
            }
            holder.desc.text = description
        }

        holder.card.setOnClickListener {
            val intent = Intent(this.context, ByRoomResultListActivity::class.java)
            intent.putExtra(Constants.FILTER_NAME, rooms[position].description)
            ContextCompat.startActivity(context, intent, null)
        }
    }

}

class ByRoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var desc: TextView = itemView.findViewById(R.id.desc)
    var img: ImageView = itemView.findViewById(R.id.img)
    var card: ConstraintLayout = itemView.findViewById(R.id.filter_card)
}
