package br.com.ladyplant.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ladyplant.R
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Preview
@Composable
fun ResultListShimmer() = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(start = 24.dp, end = 24.dp, bottom = 4.dp, top = 64.dp),
) {
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
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .shimmer(shimmerInstance)
            .height(100.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
    )
}