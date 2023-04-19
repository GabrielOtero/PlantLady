package br.com.ladyplant.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.ladyplant.BuildConfig
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.Plant
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ResultListCard(
    navController: NavHostController, plant: Plant
) {
    Card(elevation = 30.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { navController.navigate("plant_detail/${plant.id}") }
            .background(MaterialTheme.colors.surface)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(BuildConfig.IMAGES_END_POINT + plant.image)
                        .decoderFactory(SvgDecoder.Factory()).build(),
                    contentDescription = plant.name,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .padding(horizontal = 0.dp, vertical = 8.dp)
                )
                Spacer(Modifier.width(4.dp))
                CardTitleText(
                    text = (plant.name),
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .widthIn(0.dp, 170.dp)
                )
            }

            IconButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = "Voltar",
                    tint = colorResource(id = R.color.viridian_green),
                )
            }
        }

    }
}