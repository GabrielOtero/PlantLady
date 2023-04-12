package br.com.ladyplant.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.ladyplant.R

@Composable
fun TopBar(
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable (() -> Unit)? = {
        IconButton(onClick = onBackPressed) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
        }
    },
    actions: @Composable RowScope.() -> Unit = {},
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = title,
        actions = actions,
        elevation = 0.dp,
        navigationIcon = navigationIcon,
        backgroundColor = colorResource(id = R.color.white_smoke),
        contentColor = colorResource(id = R.color.viridian_green)
    )
}