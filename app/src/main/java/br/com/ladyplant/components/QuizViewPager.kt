package br.com.ladyplant.components

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class QuizViewPager(
    context: Context,
    attrs: AttributeSet
) : ViewPager(context, attrs) {
    var position: Int = 0

    fun goPrevious() {
        this.setCurrentItem(this.currentItem - 1, true)
        position = this.currentItem + 1

    }

    fun goNext() {
        this.setCurrentItem(this.currentItem + 1, true)
        position = this.currentItem + 1
    }
}
