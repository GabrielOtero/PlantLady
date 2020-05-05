package br.com.ladyplant

import android.content.Context
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.ladyplant.details.DetailActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        val constraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val frameLayout = constraintLayout.findViewById(R.id.activity_content) as FrameLayout

        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(constraintLayout)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }


    fun showLoading() {
        Log.d(TAG, getString(R.string.log_show_loading))
        progress_bar.visibility = VISIBLE
    }

    fun hideLoading() {
        Log.d(TAG, getString(R.string.log_hide_loading))
        progress_bar.visibility = GONE
    }

    companion object {
        private const val TAG = "BaseActivity"
    }

}
