package br.com.ladyplant.ui.navigation

import br.com.ladyplant.R

sealed class NavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : NavItem("Home", R.drawable.ic_home, "home")
    object Explore : NavItem("Explore", R.drawable.ic_search, "explore")
    object Quiz : NavItem("Quiz", R.drawable.ic_search, "quiz")

}