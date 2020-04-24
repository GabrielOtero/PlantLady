package br.com.ladyplant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity() {

    private var byTypeAdapter: FilterByTypeAdapter = FilterByTypeAdapter()
    private var byRoomAdapter: FilterByRoomAdapter = FilterByRoomAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        initRecyclersView()
    }

    private fun initRecyclersView() {
        byTypeAdapter = FilterByTypeAdapter()
        by_type_list.adapter = byTypeAdapter
        by_type_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        byRoomAdapter = FilterByRoomAdapter()
        by_room_list.adapter = byRoomAdapter
        by_room_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
    }
}
