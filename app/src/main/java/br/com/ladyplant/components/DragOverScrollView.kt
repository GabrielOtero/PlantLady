package br.com.ladyplant.components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class DragOverScrollView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.actionMasked == MotionEvent.ACTION_DOWN) {
            return false
        }
        return super.onTouchEvent(ev)
    }
}
