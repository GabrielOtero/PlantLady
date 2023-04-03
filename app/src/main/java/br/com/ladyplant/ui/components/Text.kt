package br.com.ladyplant.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.ladyplant.R

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) = Text(
    text = text.lowercase(),
    modifier = modifier,
    maxLines = 1,
    style = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = R.color.viridian_green),
    )
)

@Composable
fun SubTitleText(text: String, modifier: Modifier = Modifier) = Text(
    text = text.uppercase(),
    modifier = modifier,
    maxLines = 1,
    style = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = R.color.viridian_green),
    )
)

@Composable
fun PreFilterLabel(text: String, modifier: Modifier = Modifier) = Text(
    text = text.lowercase(),
    modifier = modifier,
    maxLines = 1,
    style = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = R.color.black),
    )
)
