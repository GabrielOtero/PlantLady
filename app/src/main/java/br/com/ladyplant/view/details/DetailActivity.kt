package br.com.ladyplant.view.details


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.BuildConfig.IMAGES_END_POINT
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.data.repository.Resource
import br.com.ladyplant.data.repository.Status
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity() {

    private val viewModel by viewModel<DetailViewModel>()

    private val observer = Observer<Resource<Plant>> {
        when (it.status) {
            Status.SUCCESS -> showPlant(it.data)
            Status.ERROR -> showError(it.message)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel.plantLV.observe(this, observer)

        val plantId = intent.extras?.getInt(Constants.EXTRA_PLANT_ID)

        if (plantId != null) {
            viewModel.onViewCreated(plantId)
        } else {
            Log.d(TAG, "invalid ID")
        }

    }

    private fun showError(message: String?) {
        Log.d(TAG, message!!)
    }

    private fun showPlant(plant: Plant?) {
        plant?.let { p ->
            GlideToVectorYou.justLoadImage(
                this,
                Uri.parse(IMAGES_END_POINT + p.image),
                your_plant_image
            )

            your_plant.text = p.name.toLowerCase()
            your_plant_is_scientific_name.text = getString(
                R.string.result_activity_your_plant_family, p.scientificName
            )
            origin.text = p.origin
            poisonous.text = p.poisonous
            light.text = p.light
            water.text = p.water
            overview.text = p.overview

            hideLoading()
        }
    }

    companion object {
        private const val TAG = "DetailActivity"
    }


}



