package br.com.ladyplant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterByRoomAdapter: RecyclerView.Adapter<ByRoomViewHolder>() {

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
                else -> holder.img.setImageResource(R.drawable.ic_filter_by_type_cactus)
            }
            holder.desc.text = description
        }
    }

}

class ByRoomViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var desc: TextView = itemView.findViewById(R.id.desc)
    var img: ImageView = itemView.findViewById(R.id.img)
}
