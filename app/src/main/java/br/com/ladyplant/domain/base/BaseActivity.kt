package br.com.ladyplant.domain.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.ladyplant.R
import com.google.firebase.analytics.FirebaseAnalytics
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {

    var firebaseAnalytics : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)

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

    fun showError(message: String?) {
        Log.d(TAG, message!!)
        val bundle = Bundle()
        bundle.putString("msg", message)
        firebaseAnalytics.logEvent("ops_screen", bundle)
        hideLoading()
        error.visibility = VISIBLE
    }

    companion object {
        private const val TAG = "BaseActivity"
    }

}
