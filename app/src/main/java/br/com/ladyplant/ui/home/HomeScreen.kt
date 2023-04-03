package br.com.ladyplant.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.ladyplant.R
import br.com.ladyplant.ui.components.Carousel
import br.com.ladyplant.ui.components.CarouselItem
import br.com.ladyplant.ui.components.TitleText
import br.com.ladyplant.ui.navigation.NavItem

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(), navController: NavController
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 16.dp)
    ) {

        Column(
        ) {
            TitleText(
                text = "home",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            Carousel(
                title = "popular plants",
                items = listOf(
                    CarouselItem(
                        id = 1, imageRes = R.drawable.ic_filter_by_type_cactus, title = "Cactus"
                    ),
                    CarouselItem(
                        id = 2, imageRes = R.drawable.ic_filter_by_type_flower, title = "Peace Lily"
                    ),
                    CarouselItem(
                        id = 3, imageRes = R.drawable.ic_filter_by_type_palms, title = "Areca Palm"
                    ),
                    CarouselItem(
                        id = 4, imageRes = R.drawable.ic_filter_by_type_lianas, title = "Peperomia"
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClickItem = { id ->
                    Log.d("", id.toString())
                    navController.navigate(NavItem.PlantDetail.screen_route)
                },
            )
        }
    }
}

