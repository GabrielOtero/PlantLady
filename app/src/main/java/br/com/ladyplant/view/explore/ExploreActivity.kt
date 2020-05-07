package br.com.ladyplant.view.explore

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.R
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.domain.model.ByRoomFilter
import br.com.ladyplant.domain.model.ByTypeFilter
import br.com.ladyplant.domain.model.PlantType
import br.com.ladyplant.domain.model.RoomType
import kotlinx.android.synthetic.main.activity_explore.*


class ExploreActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        initRecyclersView()

    }

    private fun initRecyclersView() {
        var byTypeAdapter = FilterByTypeAdapter(this)
        by_type_list.adapter = byTypeAdapter
        by_type_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        var byRoomAdapter = FilterByRoomAdapter(this)
        by_room_list.adapter = byRoomAdapter
        by_room_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        //MOCK
        byTypeAdapter.types = mutableListOf(
            ByTypeFilter(1, "cactus", PlantType.CACTUS),
            ByTypeFilter(2, "lianas", PlantType.LIANAS),
            ByTypeFilter(3, "palms", PlantType.PALMS),
            ByTypeFilter(4, "fruits", PlantType.FRUITS)
        )

        byRoomAdapter.rooms = mutableListOf(
            ByRoomFilter(1, "living room", RoomType.LIVING),
            ByRoomFilter(2, "bedroom", RoomType.BED),
            ByRoomFilter(3, "bathroom", RoomType.BATH),
            ByRoomFilter(4, "kitchen", RoomType.DINNING)
        )
        /////
    }
}