package br.com.ladyplant.components

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarouselRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {
    var position: Int = 0
    var onPositionChange : ((Int) -> Unit)? = null

    init {
        this.layoutManager =
            NoScrollLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            ) { newPosition ->
                position = newPosition
                onPositionChange?.let { it(newPosition) }
            }
    }

    fun onPositionChangeCallback(callback: ((Int) -> Unit)?) {
        onPositionChange = callback
    }


    fun scrollNext() {
        smoothScrollToPosition(position + 1)
    }

    fun scrollPrevious() {
        smoothScrollToPosition(position - 1)
    }
}

class NoScrollLayoutManager(
    baseContext: Context,
    orientation: Int,
    reverserLayout: Boolean,
    val onPositionUpdate: (newPosition: Int) -> Unit
) : LinearLayoutManager(baseContext, orientation, reverserLayout) {
    private var canScroll = false

    override fun canScrollHorizontally(): Boolean {
        return canScroll
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State?,
        position: Int
    ) {
        canScroll = true
        super.smoothScrollToPosition(recyclerView, state, position)
        onPositionUpdate(position)
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            canScroll = false
        }
    }
}
