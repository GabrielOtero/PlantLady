package br.com.ladyplant.view.result

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.R
import br.com.ladyplant.view.components.VerticalSpacingItemDecorator
import br.com.ladyplant.domain.model.HeaderResult
import br.com.ladyplant.domain.model.ItemResult
import kotlinx.android.synthetic.main.activity_result.*

abstract class BaseResultListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initRecyclerView()
        val headerList = items().toMutableList()
        headerList.add(0, HeaderResult(title(), subTitle()))
        (result_list.adapter as ResultAdapter).results = headerList

        if (items().size > 1) {
            empty_state_img.visibility = GONE
            empty_state_msg.visibility = GONE
        } else {
            empty_state_img.visibility = VISIBLE
            empty_state_msg.visibility = VISIBLE
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
    abstract fun items(): List<ItemResult>
}
