package br.com.ladyplant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.ladyplant.ui.QuizScreen
import br.com.ladyplant.ui.explore.ExploreScreen
import br.com.ladyplant.ui.home.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Home.screen_route) {
        composable(NavItem.Home.screen_route) { HomeScreen(navController = navController) }
        composable(NavItem.Explore.screen_route) { ExploreScreen() }
        composable(NavItem.Quiz.screen_route) { QuizScreen() }
    }
}