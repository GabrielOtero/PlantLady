package br.com.ladyplant

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        val navBackStackEntry by navController.currentBackStackEntryAsState()


        showBottomBar = when (navBackStackEntry?.destination?.route) {
            NavItem.Quiz.screen_route -> false
            else -> true
        }

        Scaffold(
            bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) },
        ) {
            NavigationGraph(navController = navController)
        }
    }
}
