package br.com.ladyplant.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ladyplant.R

@Composable
fun QuizOptionButton(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.viridian_green)),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(id = R.color.black),
            backgroundColor = colorResource(R.color.transparent)
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular)),
            ),
        )
    }
}