package br.com.ladyplant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilterByRoomAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var rooms: MutableList<ByRoomFilter> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_by_room_filter, parent, false)
        return ByTypeViewHolder(view)
    }

    override fun getItemCount() = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

}

class ByRoomViewHolder(view: View) : RecyclerView.ViewHolder(view)
