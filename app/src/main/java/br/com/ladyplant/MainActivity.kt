package br.com.ladyplant

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.ladyplant.navigation.BottomNavigation
import br.com.ladyplant.navigation.NavItem
import br.com.ladyplant.navigation.NavigationGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var mInterstitialAd: InterstitialAd? = null

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)

        loadInterstitial(this)
        setContent {
            MainScreenView()
        }

        // https://issuetracker.google.com/issues/227926002
        lifecycleScope.launch {
            delay(50)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    @Composable
    fun MainScreenView() {
        val navController = rememberNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        var showTopBar by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        SetStatusBarColor()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            NavItem.Home.screen_route -> true
            NavItem.Explore.screen_route -> true
            else -> false
        }

        showTopBar = when (navBackStackEntry?.destination?.route) {
            NavItem.PlantDetail.screen_route -> true
            else -> false
        }

        Scaffold(
            bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) },
            backgroundColor = colorResource(id = R.color.white_smoke)
        ) { p ->
            NavigationGraph(navController = navController)
        }
    }

    @Composable
    fun SetStatusBarColor(color: Color = colorResource(id = R.color.white_smoke)) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        val navigationColor = colorResource(id = R.color.white_smoke)

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setStatusBarColor(
                color = color, darkIcons = useDarkIcons
            )
            systemUiController.setNavigationBarColor(
                color = navigationColor, darkIcons = useDarkIcons
            )
            onDispose {}
        }
    }
}

fun goToSomethingWentWrongScreen(
    navController: NavController
) {
    navController.navigate(
        NavItem.SomethingWentWrong.screen_route,
        NavOptions.Builder().setPopUpTo(NavItem.SomethingWentWrong.screen_route, true).build()
    )
}

fun showInterstitial(context: Context, onAdDismissed: () -> Unit) {
    val activity = context.findActivity()

    if (mInterstitialAd != null && activity != null) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(e: AdError) {
                mInterstitialAd = null
                onAdDismissed()
            }

            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd = null

                loadInterstitial(context)
                onAdDismissed()
            }
        }
        mInterstitialAd?.show(activity)
    } else {
        onAdDismissed()
    }
}

private fun loadInterstitial(context: Context) {
    InterstitialAd.load(
        context,
        BuildConfig.INTERSTITIAL_AD_ID,
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                Log.e("LoadAdError", adError.message)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        }
    )
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
