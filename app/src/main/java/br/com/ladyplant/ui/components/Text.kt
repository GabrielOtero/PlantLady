package br.com.ladyplant.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import br.com.ladyplant.R

@Composable
fun TitleText(text: String, modifier: Modifier) = Text(
    text = text,
    modifier = modifier,
    maxLines = 1,
    fontSize = 32.sp,
    color = colorResource(id = R.color.viridian_green)
)
