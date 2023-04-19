package br.com.ladyplant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.ladyplant.ui.navigation.BottomNavigation
import br.com.ladyplant.ui.navigation.NavItem
import br.com.ladyplant.ui.navigation.NavigationGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView()
        }
    }

    @Preview
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
