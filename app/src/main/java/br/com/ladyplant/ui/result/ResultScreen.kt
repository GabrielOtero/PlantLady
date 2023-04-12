package br.com.ladyplant.ui.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultScreen(viewModel: ResultListViewModel = hiltViewModel()) {
    Text("Result")
}