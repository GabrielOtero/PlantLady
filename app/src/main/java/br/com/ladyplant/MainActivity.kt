package br.com.ladyplant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
            else -> false
        }

        Scaffold(
            bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) },
            topBar = { if (showTopBar) TopBar() },
            backgroundColor = colorResource(id = R.color.white_smoke)
        ) {
            NavigationGraph(navController = navController)
        }
    }

    @Composable
    fun TopBar() {
        TopAppBar(
            title = {},
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Voltar"
                    )
                }
            },
            backgroundColor = colorResource(id = R.color.white_smoke),
            contentColor = colorResource(id = R.color.viridian_green)
        )
    }
}
