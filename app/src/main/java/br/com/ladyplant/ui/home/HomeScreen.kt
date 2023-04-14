package br.com.ladyplant.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.ladyplant.R
import br.com.ladyplant.ui.components.Carousel
import br.com.ladyplant.ui.components.CarouselItem
import br.com.ladyplant.ui.components.DescriptionText
import br.com.ladyplant.ui.components.TitleText
import br.com.ladyplant.ui.navigation.NavItem


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(), navController: NavController
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        LazyColumn(
        ) {
            item {
                TitleText(
                    text = "home",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 24.dp),
                )
            }
            item {
                Carousel(
                    title = "popular plants",
                    items = listOf(
                        CarouselItem(
                            id = 1, imageRes = R.drawable.ic_filter_by_type_cactus, title = "Cactus"
                        ),
                        CarouselItem(
                            id = 2,
                            imageRes = R.drawable.ic_filter_by_type_flower,
                            title = "Peace Lily"
                        ),
                        CarouselItem(
                            id = 3,
                            imageRes = R.drawable.ic_filter_by_type_palms,
                            title = "Areca Palm"
                        ),
                        CarouselItem(
                            id = 4,
                            imageRes = R.drawable.ic_filter_by_type_lianas,
                            title = "Peperomia"
                        ),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClickItem = { id ->
                        navController.navigate("plant_detail/$id")
                    },
                )
            }
            item { Spacer(modifier = Modifier.height(64.dp)) }
            item { QuizCard(navController) }

        }
    }
}

@Composable
private fun QuizCard(navController: NavController) {
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.clickable {
        navController.navigate(NavItem.Quiz.screen_route)
    }) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.default_gradient_start),
                            colorResource(id = R.color.default_gradient_middle),
                            colorResource(id = R.color.default_gradient_end),
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowForward, contentDescription = "Quiz", tint = colorResource(
                    id = R.color.white
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(
                text = "take the quiz",
                color = R.color.white,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
            DescriptionText(
                text = "and find your next plant", color = R.color.white, letterSpacing = 2.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_palm_sample),
            contentDescription = "",
            modifier = Modifier.matchParentSize(),
            alignment = Alignment.TopEnd
        )
    }
}

