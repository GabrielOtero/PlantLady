package br.com.ladyplant

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_explore.by_room_list
import kotlinx.android.synthetic.main.activity_explore.by_type_list
import kotlinx.android.synthetic.main.activity_explore.*


class ExploreActivity : AppCompatActivity(), View.OnClickListener, View.OnScrollChangeListener {
    private var byTypeAdapter: FilterByTypeAdapter = FilterByTypeAdapter()
    private var byRoomAdapter: FilterByRoomAdapter = FilterByRoomAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        initRecyclersView()
        setOnClickListeners()

        pre_filter_container.setOnScrollChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            arrow_up -> {
                pre_filter_container.fullScroll(View.FOCUS_DOWN)
                showHideView(viewToShow = arrow_down, viewToHide= arrow_up)
            }
            arrow_down -> {
                pre_filter_container.fullScroll(View.FOCUS_UP)
                showHideView(viewToShow = arrow_up, viewToHide= arrow_down)
            }
        }
    }

    override fun onScrollChange(
        v: View?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        showHideView(viewToShow = arrow_down, viewToHide= arrow_up)
        if (scrollY == 0) {
            showHideView(viewToShow = arrow_up, viewToHide= arrow_down)
        }
    }

    private fun showHideView(viewToShow: View, viewToHide: View) {
        viewToShow.visibility = VISIBLE
        viewToHide.visibility = GONE
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

        //MOCK
        byTypeAdapter.types = mutableListOf(
            ByTypeFilter("cactus", PlantType.CACTUS),
            ByTypeFilter("lianas", PlantType.LIANAS),
            ByTypeFilter("palms", PlantType.PALMS),
            ByTypeFilter("fruits", PlantType.FRUITS),
            ByTypeFilter("cactus", PlantType.CACTUS)
        )

        byRoomAdapter.rooms = mutableListOf(
            ByRoomFilter("living room", RoomType.LIVING),
            ByRoomFilter("bedroom", RoomType.BED),
            ByRoomFilter("bathroom", RoomType.BATH),
            ByRoomFilter("kitchen", RoomType.DINNING)
        )
        /////
    }

    private fun setOnClickListeners() {
        arrow_up.setOnClickListener(this)
        arrow_down.setOnClickListener(this)
    }
}