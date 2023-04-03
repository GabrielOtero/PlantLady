package br.com.ladyplant.ui.plantDetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ladyplant.ui.components.TitleText

@Composable
fun PlantDetailScreen() {
    TitleText(
        text = "Boston Fern",
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}