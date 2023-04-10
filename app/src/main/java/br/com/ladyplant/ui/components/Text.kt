package br.com.ladyplant.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import br.com.ladyplant.R

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Int = R.color.viridian_green
) = Text(
    text = text.lowercase(), modifier = modifier, maxLines = 1, style = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = color),
    )
)

@Composable
fun SubTitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Int = R.color.viridian_green
) = Text(
    text = text.uppercase(), modifier = modifier, maxLines = 1, style = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = color),
    )
)

@Composable
fun DescriptionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Int = R.color.viridian_green,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Light,
    letterSpacing: TextUnit = TextUnit.Unspecified
) = Text(
    text = text, modifier = modifier, style = TextStyle(
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = color),
        letterSpacing = letterSpacing
    )
)

@Composable
fun PreFilterLabel(text: String, modifier: Modifier = Modifier) = Text(
    text = text.lowercase(), modifier = modifier, maxLines = 1, style = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
        color = colorResource(id = R.color.black),
    )
)
