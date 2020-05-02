package br.com.ladyplant.result

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.BaseActivity
import br.com.ladyplant.R
import br.com.ladyplant.components.VerticalSpacingItemDecorator
import br.com.ladyplant.model.PlantResultWrapper
import kotlinx.android.synthetic.main.activity_result.*

abstract class BaseResultListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result_title.text = title()
        result_subtitle.text = subTitle()

        initRecyclerView()

        if (items().isNotEmpty()) {
            (result_list.adapter as ResultAdapter).results = items()
            empty_state_img.visibility = GONE
            empty_state_msg.visibility = GONE
            result_list.visibility = VISIBLE
        }else {
            empty_state_img.visibility = VISIBLE
            empty_state_msg.visibility = VISIBLE
            result_list.visibility = GONE
        }
    }

    private fun initRecyclerView() {
        var resultAdapter = ResultAdapter(this)
        result_list.adapter = resultAdapter
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(32)
        result_list.addItemDecoration(verticalSpacingItemDecorator)
        result_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
    }

    abstract fun title(): String
    abstract fun subTitle(): String
    abstract fun items(): List<PlantResultWrapper>
}
