package br.com.ladyplant.ui.plantDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.ladyplant.BuildConfig.IMAGES_END_POINT
import br.com.ladyplant.R
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.ui.components.DescriptionText
import br.com.ladyplant.ui.components.SubTitleText
import br.com.ladyplant.ui.components.TitleText
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer


@Composable
fun PlantDetailScreen(
    viewModel: PlantDetailViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var showShimmer by remember { mutableStateOf(true) }
    val plant by viewModel.plant.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.viewState.loading.observe(
            lifecycleOwner
        ) { loading ->
            showShimmer = loading
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        if (showShimmer) PlantDetailShimmer()
        else {
            plant?.let { PlantDetailComponent(it) }
        }
    }
}

@Composable
private fun PlantDetailComponent(plant: Plant) = Column(

) {
    TitleText(
        text = plant.name, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(10.dp))
    SubTitleText(
        text = "(${plant.scientificName})",
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(40.dp))
    Box(
        Modifier.align(alignment = Alignment.CenterHorizontally)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_image_background),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(IMAGES_END_POINT + plant.image)
                .decoderFactory(SvgDecoder.Factory()).build(),
            contentDescription = plant.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize()
        )

    }
    Spacer(modifier = Modifier.height(40.dp))
    SubTitleText(
        text = stringResource(R.string.detail_activity_origin_desc_title),
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionText(
        text = plant.origin, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))
    SubTitleText(
        text = stringResource(R.string.detail_activity_light_desc_title),
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionText(
        text = plant.light, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))
    SubTitleText(
        text = stringResource(R.string.detail_activity_water_desc_title),
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionText(
        text = plant.water, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))
    SubTitleText(
        text = stringResource(R.string.detail_activity_poisonous_desc_title),
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionText(
        text = plant.poisonous, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))
    SubTitleText(
        text = stringResource(R.string.detail_activity_overview_desc_title),
        color = R.color.dark_gray,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    DescriptionText(
        text = plant.overview, color = R.color.dark_gray, modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))

}

@Composable
private fun PlantDetailShimmer() = Column {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(30.dp)
            .width(230.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(200.dp)

    )
    Spacer(modifier = Modifier.height(40.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(200.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(40.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(80.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(180.dp)

    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(80.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(180.dp)

    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(80.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(180.dp)

    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(80.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(180.dp)

    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(80.dp)

    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(300.dp)

    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(310.dp)

    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(305.dp)

    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(310.dp)

    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .background(colorResource(id = R.color.light_gray))
            .height(20.dp)
            .width(300.dp)

    )
}
