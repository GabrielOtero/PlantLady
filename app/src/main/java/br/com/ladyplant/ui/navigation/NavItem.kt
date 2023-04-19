package br.com.ladyplant.ui.navigation

import br.com.ladyplant.R

sealed class NavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : NavItem("Home", R.drawable.ic_icon_home, "home")
    object Explore : NavItem("Explore", R.drawable.ic_icon_explore, "explore")
    object Quiz : NavItem("Quiz", R.drawable.ic_search, "quiz")
    object PlantDetail : NavItem("PlantDetail", R.drawable.ic_search, "plant_detail/{plantId}")
    object Result : NavItem("Result", R.drawable.ic_search, "result/{list}")
    object SomethingWentWrong : NavItem("SomethingWentWrong", R.drawable.ic_search, "somethingWentWrong")
}