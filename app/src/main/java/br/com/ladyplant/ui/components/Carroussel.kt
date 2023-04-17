package br.com.ladyplant.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import br.com.ladyplant.R

@Composable
fun Carousel(
    title: String,
    titleColor: Int = R.color.viridian_green,
    scrollColor: Int = R.color.viridian_green,
    items: List<CarouselItem>,
    modifier: Modifier = Modifier,
    onClickItem: (Int) -> Unit = { }
) {
    val spacedItems: List<CarouselItem?> = listOf(null) + items + listOf(null)
    Column {
        SubTitleText(
            text = title,
            color = titleColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        val scrollState = rememberScrollState()
        Row(
            Modifier
                .horizontalScroll(scrollState)
                .fillMaxWidth()
        ) {
            spacedItems.forEach { item ->
                if (item == null) {
                    Spacer(modifier = Modifier.width(16.dp))
                } else {
                    CarouselCard(item, onClickItem)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val parentWidth = 100.dp
        Row(
            modifier = Modifier
                .width(parentWidth)
                .height(7.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = scrollColor).copy(alpha = 0.30f))
                .align(CenterHorizontally)

        ) {
            val paddingStart = ((parentWidth - 27.dp) / scrollState.maxValue.dp) * scrollState.value.dp
            Text(
                "ABC",
                color = colorResource(id = scrollColor),
                modifier = Modifier
                    .padding(start = paddingStart)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(id = scrollColor))

            )
        }

    }
}

data class CarouselItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
)

@Composable
fun CarouselCard(
    item: CarouselItem, onClickItem: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { onClickItem(item.id) })
            .width(136.dp)
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.FillWidth,
        )
        PreFilterLabel(
            text = item.title,
            modifier = Modifier
                .padding(bottom = 14.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

