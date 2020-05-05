package br.com.ladyplant.details


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import br.com.ladyplant.BaseActivity
import br.com.ladyplant.R
import br.com.ladyplant.model.Plant
import br.com.ladyplant.repository.Resource
import br.com.ladyplant.repository.Status
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity() {

    private val viewModel by viewModel<DetailViewModel>()

    private val observer = Observer<Resource<Plant>> {
        when (it.status) {
            Status.SUCCESS -> showPlant(it.data!!)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel.plantLV.observe(this, observer)

        viewModel.onViewCreated(1)

        GlideToVectorYou.justLoadImage(
            this,
            Uri.parse("https://yardman-qa.herokuapp.com/images/plant/golden_barrel_cactus.svg"),
            your_plant_image
        )
    }

    private fun showError(message: String) {
        Log.d(TAG, message)
    }

    private fun showPlant(plant: Plant) {
        hideLoading()
        Log.d(TAG, plant.toString())
    }

    companion object {
        private const val TAG = "DetailActivity"
    }


}



