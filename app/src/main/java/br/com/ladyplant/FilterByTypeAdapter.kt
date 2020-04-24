package br.com.ladyplant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilterByTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var types: MutableList<ByTypeFilter> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_by_type_filter, parent, false)
        return ByTypeViewHolder(view)
    }

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

}

class ByTypeViewHolder(view: View) : RecyclerView.ViewHolder(view)
