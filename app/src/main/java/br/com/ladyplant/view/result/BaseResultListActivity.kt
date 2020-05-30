package br.com.ladyplant.view.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ladyplant.R
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.HeaderResult
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.view.components.VerticalSpacingItemDecorator
import br.com.ladyplant.view.details.DetailActivity
import br.com.ladyplant.view.quiz.QuizActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_result.*

abstract class BaseResultListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val loadImageListener = object : LoadImageListener {
            override fun onLoad(uri: Uri, image: ImageView) {
                GlideToVectorYou.justLoadImage(
                    this@BaseResultListActivity, uri, image
                )
            }

        }
        val takeQuizAgainClickListener = object : TakeQuizAgainClickListener {
            override fun onClick() {
                startActivity(Intent(this@BaseResultListActivity, QuizActivity::class.java))
            }
        }
        val itemResultClickListener = object : ItemResultClickListener {
            override fun onClick(id: Int) {
                val intent = Intent(this@BaseResultListActivity, DetailActivity::class.java)
                intent.putExtra(Constants.EXTRA_PLANT_ID, id)
                startActivity(intent)
            }

        }
        val resultAdapter =
            ResultAdapter(loadImageListener, takeQuizAgainClickListener, itemResultClickListener)
        val verticalSpacingItemDecorator = VerticalSpacingItemDecorator(32)

        result_list.adapter = resultAdapter
        result_list.addItemDecoration(verticalSpacingItemDecorator)
        result_list.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
    }

    fun setItems(items: List<ItemResult>) {
        showEmptyState(items.isEmpty())

        val listWithHeader = items.toMutableList()
        listWithHeader.add(0, HeaderResult(title(), subTitle()))

        (result_list.adapter as ResultAdapter).results = listWithHeader
        hideLoading()
    }

    private fun showEmptyState(show: Boolean) {
        if (show) {
            empty_state_img.visibility = VISIBLE
            empty_state_msg.visibility = VISIBLE
            firebaseAnalytics.logEvent("empty_state", null)
        } else {
            empty_state_img.visibility = GONE
            empty_state_msg.visibility = GONE
        }

    }

    abstract fun title(): String
    abstract fun subTitle(): String
}

interface TakeQuizAgainClickListener {
    fun onClick()
}

interface ItemResultClickListener {
    fun onClick(id: Int)
}

interface LoadImageListener {
    fun onLoad(parse: Uri, image: ImageView)
}