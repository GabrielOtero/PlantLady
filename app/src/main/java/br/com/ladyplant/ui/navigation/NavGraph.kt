package br.com.ladyplant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.ladyplant.ui.result.SomethingWentWrongScreen
import br.com.ladyplant.ui.explore.ExploreScreen
import br.com.ladyplant.ui.home.HomeScreen
import br.com.ladyplant.ui.plantDetail.PlantDetailScreen
import br.com.ladyplant.ui.quizz.QuizScreen
import br.com.ladyplant.ui.result.ResultScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavItem.Home.screen_route) {
        composable(NavItem.Home.screen_route) { HomeScreen(navController = navController) }
        composable(NavItem.Explore.screen_route) { ExploreScreen(navController = navController,) }
        composable(NavItem.Quiz.screen_route) { backStackEntry ->
            QuizScreen(
                backStackEntry = backStackEntry,
                navController = navController,
            )
        }
        composable(
            NavItem.PlantDetail.screen_route,
            arguments = listOf(navArgument("plantId") { type = NavType.IntType })
        ) { PlantDetailScreen(navController = navController) }
        composable(
            NavItem.Result.screen_route, arguments = listOf(navArgument("list") {
                type = PlantListNavType
                defaultValue = PlantList(emptyList())
            })
        ) {
            ResultScreen(
                navController = navController,
            )
        }
        composable(NavItem.SomethingWentWrong.screen_route) { SomethingWentWrongScreen(navController = navController) }
    }
}