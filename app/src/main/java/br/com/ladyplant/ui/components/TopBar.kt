package br.com.ladyplant.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.ladyplant.R

@Composable
fun TopBar(showContent: Boolean, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
                if(showContent) {

                }
        },
        elevation = 0.dp,
        navigationIcon = {
            if(showContent) {
                IconButton(onClick = { onBackPressed() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Voltar"
                    )
                }
            }
        },
        backgroundColor = colorResource(id = R.color.white_smoke),
        contentColor = colorResource(id = R.color.viridian_green)
    )
}