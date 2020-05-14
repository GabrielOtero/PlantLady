package br.com.ladyplant.view.explore

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.R
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.domain.model.*
import br.com.ladyplant.view.result.byText.ByTextResultListActivity
import kotlinx.android.synthetic.main.activity_explore.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExploreActivity : BaseActivity(), TextView.OnEditorActionListener {

    private val viewModel by viewModel<ExploreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        initRecyclersView()
        search_plant.setOnEditorActionListener(this)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            goToTextResultList()
            return true
        }
        return false
    }

    private fun goToTextResultList() {
        if (search_plant.text.toString().isNotEmpty()) {
            val intent = Intent(this, ByTextResultListActivity::class.java)
            intent.putExtra(Constants.EXTRA_FILTER_TEXT, search_plant.text.toString())
            startActivity(intent)
        }
    }

    private fun initRecyclersView() {
        val byTypeAdapter = FilterByTypeAdapter(this)
        by_type_list.adapter = byTypeAdapter
        by_type_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        val byRoomAdapter = FilterByRoomAdapter(this)
        by_room_list.adapter = byRoomAdapter
        by_room_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

        //MOCK
        byTypeAdapter.types = mutableListOf(
            ByTypeFilter(1, "cactus", PlantType.CACTUS),
            ByTypeFilter(2, "lianas", PlantType.LIANAS),
            ByTypeFilter(3, "palms", PlantType.PALMS),
            ByTypeFilter(4, "indoor", PlantType.INDOOR)
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