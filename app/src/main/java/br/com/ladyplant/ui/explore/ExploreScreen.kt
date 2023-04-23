package br.com.ladyplant.ui.explore

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.goToSomethingWentWrongScreen
import br.com.ladyplant.ui.components.Carousel
import br.com.ladyplant.ui.components.CarouselItem
import br.com.ladyplant.ui.components.DescriptionText
import br.com.ladyplant.ui.components.ResultListShimmer
import br.com.ladyplant.ui.components.TitleText
import br.com.ladyplant.ui.navigation.NavItem
import br.com.ladyplant.ui.navigation.navTypes.PlantList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ExploreScreen(viewModel: ExploreViewModel = hiltViewModel(), navController: NavHostController) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var showShimmer by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.viewState.loading.observe(
            lifecycleOwner
        ) { loading ->
            showShimmer = loading
        }

        viewModel.viewState.action.observe(
            lifecycleOwner
        ) { action ->
            when (action) {
                is ExploreViewState.Action.GoToResultList -> {
                    goToResultScreen(action.resultList, navController)
                }
                is ExploreViewState.Action.ShowError -> {
                    goToSomethingWentWrongScreen(navController)
                }
            }
        }
    }

    if (showShimmer) ResultListShimmer()
    else {
        ExploreScreenComponent(viewModel, navController)
    }
}

@Composable
private fun ExploreScreenComponent(
    viewModel: ExploreViewModel,
    navController: NavHostController,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.default_gradient_end),
                        colorResource(id = R.color.default_gradient_middle),
                        colorResource(id = R.color.default_gradient_start),
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                TitleText(
                    text = "explore",
                    color = R.color.white,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 24.dp),
                )
            }
            item { ByTypeCarousel(viewModel) }
            item { Spacer(Modifier.height(32.dp)) }
            item { ByRoomCarousel(viewModel) }
            item { Spacer(Modifier.height(24.dp)) }
            item { FeelingLostCard(navController) }
            item { Spacer(Modifier.height(112.dp)) }
        }
    }
}

@Composable
private fun ByTypeCarousel(viewModel: ExploreViewModel) {
    Carousel(
        title = "BY TYPE",
        titleColor = R.color.white,
        scrollColor = R.color.light_grey,
        items = listOf(
            CarouselItem(
                id = 1, imageRes = R.drawable.ic_filter_by_type_cactus, title = "Cactus"
            ),
            CarouselItem(
                id = 2, imageRes = R.drawable.ic_filter_by_type_hanging, title = "Hanging"
            ),
            CarouselItem(
                id = 5, imageRes = R.drawable.ic_filter_by_type_flower, title = "Flowers"
            ),
            CarouselItem(
                id = 3, imageRes = R.drawable.ic_filter_by_type_palms, title = "Palms"
            ),
            CarouselItem(
                id = 4, imageRes = R.drawable.ic_filter_by_type_indoor, title = "Indoor"
            ),
        ),
    ) {
        viewModel.dispatchViewAction(ExploreViewAction.GetPlantByType(it))
    }
}

@Composable
private fun ByRoomCarousel(viewModel: ExploreViewModel) {
    Carousel(
        title = "BY ROOM",
        titleColor = R.color.white,
        scrollColor = R.color.light_grey,
        items = listOf(
            CarouselItem(
                id = 1,
                imageRes = R.drawable.ic_filter_by_room_living_room,
                title = "living room",
            ),
            CarouselItem(
                id = 2,
                imageRes = R.drawable.ic_filter_by_room_bathroom,
                title = "Bathroom",
            ),
            CarouselItem(
                id = 3,
                imageRes = R.drawable.ic_filter_by_room_bedroom,
                title = "Bedroom",
            ),
            CarouselItem(
                id = 4,
                imageRes = R.drawable.ic_filter_by_room_diningroom,
                title = "Kitchen",
            ),
        ),
    ) {
        viewModel.dispatchViewAction(ExploreViewAction.GetPlantByRoom(it))
    }
}

@Composable
private fun FeelingLostCard(navController: NavController) {
    Box(
        contentAlignment = Alignment.TopEnd,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(R.color.light_grey)
                )
                .clickable {
                    navController.navigate(NavItem.Quiz.screen_route)
                }
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Quiz",
                tint = colorResource(
                    id = R.color.teal_green
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(
                text = "feeling lost?",
                color = R.color.teal_green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
            DescriptionText(
                text = "take the quiz and find your plant",
                color = R.color.teal_green,
                letterSpacing = 2.sp
            )
        }
    }
}

private fun goToResultScreen(
    resultList: ArrayList<Plant>,
    navController: NavController
) {
    val list = PlantList(resultList)
    val resultListArg = Uri.encode(Json.encodeToString(list))
    navController.navigate(
        "result/$resultListArg",
        NavOptions.Builder().setPopUpTo(NavItem.Quiz.screen_route, true).build()
    )
}
