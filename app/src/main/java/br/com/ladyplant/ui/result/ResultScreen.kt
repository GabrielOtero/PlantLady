package br.com.ladyplant.ui.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.ladyplant.R
import br.com.ladyplant.ui.components.ResultListCard
import br.com.ladyplant.ui.components.SubTitleText
import br.com.ladyplant.ui.components.TitleText
import br.com.ladyplant.ui.components.TopBar

@Composable
fun ResultScreen(
    navController: NavHostController, viewModel: ResultListViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ResultScreenHeader(navController)
        }
        items(viewModel.plantList.size) { index ->
            val plant = viewModel.plantList[index]
            ResultListCard(navController, plant)
        }
    }
}

@Composable
private fun ResultScreenHeader(navController: NavHostController) {
    TopBar {
        navController.popBackStack()
    }
    TitleText(
        text = "results",
        color = R.color.black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 4.dp),
    )
    SubTitleText(
        text = "here are some options for you",
        color = R.color.dark_gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 12.dp),
    )
}