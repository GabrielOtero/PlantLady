package br.com.ladyplant.details


import android.net.Uri
import android.os.Bundle
import br.com.ladyplant.BaseActivity
import br.com.ladyplant.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        GlideToVectorYou.justLoadImage(
            this,
            Uri.parse("https://yardman-qa.herokuapp.com/images/plant/golden_barrel_cactus.svg"),
            your_plant_image
        )
    }
}
