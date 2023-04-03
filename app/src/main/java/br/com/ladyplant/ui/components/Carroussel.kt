package br.com.ladyplant.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Carousel(
    title: String,
    items: List<CarouselItem>,
    modifier: Modifier = Modifier,
    onClickItem: (Int) -> Unit = { }
) {
    val spacedItems: List<CarouselItem?> = listOf(null) + items + listOf(null)

    Column {
        SubTitleText(
            text = title,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyRow {
            items(spacedItems.size) { index ->
                val item = spacedItems[index]
                if (item == null) {
                    Spacer(modifier = Modifier.width(8.dp)) // Define o tamanho do espaçamento desejado
                } else {
                    CarouselCard(item, onClickItem)
                }
            }
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

