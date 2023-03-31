package br.com.ladyplant.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.ladyplant.ui.navigation.NavItem
import br.com.ladyplant.ui.components.TitleText

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {

        Column(
        ) {
            TitleText(
                text = "home",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            Text(
                text = "Popular Plants",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    navController.navigate(NavItem.Quiz.screen_route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Take the Quiz")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    homeViewModel.method()
                    navController.navigate(NavItem.Explore.screen_route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cactus")
            }
        }
    }
}

