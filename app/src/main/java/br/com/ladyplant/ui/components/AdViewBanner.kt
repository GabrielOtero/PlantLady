package br.com.ladyplant.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import br.com.ladyplant.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdViewBanner(
    adSize: AdSize = AdSize.BANNER, modifier: Modifier = Modifier
) {
    val adRequest = AdRequest.Builder().build()

    AndroidView(modifier = modifier, factory = { context ->
        AdView(context).apply {
            setAdSize(adSize)
            adUnitId = BuildConfig.BANNER_AD_ID
            loadAd(adRequest)
        }
    })
}