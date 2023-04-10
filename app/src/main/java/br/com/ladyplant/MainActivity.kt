package br.com.ladyplant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.ladyplant.ui.components.TopBar
import br.com.ladyplant.ui.navigation.BottomNavigation
import br.com.ladyplant.ui.navigation.NavItem
import br.com.ladyplant.ui.navigation.NavigationGraph
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


        showBottomBar = when (navBackStackEntry?.destination?.route) {
            NavItem.Home.screen_route -> true
            NavItem.Explore.screen_route -> true
            else -> false
        }

        showTopBar = when (navBackStackEntry?.destination?.route) {
            NavItem.PlantDetail.screen_route -> true
            NavItem.Quiz.screen_route -> true
            else -> false
        }

        Scaffold(
            bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) },
            topBar = { TopBar(showTopBar) { onBackPressedDispatcher.onBackPressed() } },
            backgroundColor = colorResource(id = R.color.white_smoke)
        ) { p ->
            NavigationGraph(navController = navController)
        }
    }


}
