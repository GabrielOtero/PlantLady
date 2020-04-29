package br.com.ladyplant.explore

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.BaseActivity
import br.com.ladyplant.R
import br.com.ladyplant.model.ByRoomFilter
import br.com.ladyplant.model.ByTypeFilter
import br.com.ladyplant.model.PlantType
import br.com.ladyplant.model.RoomType
import kotlinx.android.synthetic.main.activity_explore.*


class ExploreActivity : BaseActivity(), View.OnClickListener, View.OnScrollChangeListener {

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
                showHideView(viewToShow = arrow_down, viewToHide = arrow_up)
            }
            arrow_down -> {
                pre_filter_container.fullScroll(View.FOCUS_UP)
                showHideView(viewToShow = arrow_up, viewToHide = arrow_down)
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
        showHideView(viewToShow = arrow_down, viewToHide = arrow_up)
        if (scrollY == 0) {
            showHideView(viewToShow = arrow_up, viewToHide = arrow_down)
        }
    }

    private fun showHideView(viewToShow: View, viewToHide: View) {
        viewToShow.visibility = VISIBLE
        viewToHide.visibility = GONE
    }

    private fun initRecyclersView() {
        var byTypeAdapter = FilterByTypeAdapter()
        by_type_list.adapter = byTypeAdapter
        by_type_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        var byRoomAdapter = FilterByRoomAdapter()
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