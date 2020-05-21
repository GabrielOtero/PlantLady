package br.com.ladyplant.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.view.quiz.QuizViewAdapter


class QuizViewPager(
    context: Context,
    attrs: AttributeSet
) : ViewPager(context, attrs) {
    var position: Int = 0
    var onPositionChange: ((newPosition: Int) -> Unit)? = null
    private var mScroller: FixedSpeedScroller? = null

    init {
        val viewpager = ViewPager::class.java
        val scroller = viewpager.getDeclaredField("mScroller")
        scroller.isAccessible = true
        mScroller = FixedSpeedScroller(
            context,
            DecelerateInterpolator()
        )
        scroller.set(this, mScroller)

    }

    fun goPrevious() {
        this.setCurrentItem(this.currentItem - 1, true)
        position = this.currentItem
        onPositionChange?.let { it(position) }
    }

    fun goNext() {
        this.setCurrentItem(this.currentItem + 1, true)
        position = this.currentItem
        onPositionChange?.let { it(position) }
    }

    override fun onAnimationStart() {
        super.onAnimationStart()
        onPositionChange?.let { it(position) }
    }

    //Prevent User from Swiping
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    //Prevent User from Swiping
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun getAdapter(): QuizViewAdapter? {
        return super.getAdapter() as QuizViewAdapter?
    }
}


private class FixedSpeedScroller(
    context: Context?,
    interpolator: Interpolator?
) : Scroller(context, interpolator) {

    private var mDuration = Constants.QUIZ_TRANSITION_DELAY


    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}
