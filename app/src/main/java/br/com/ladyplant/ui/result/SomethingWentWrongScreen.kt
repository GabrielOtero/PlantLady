package br.com.ladyplant.ui.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.ladyplant.R
import br.com.ladyplant.ui.components.SomethingWentWrongSubTitle
import br.com.ladyplant.ui.components.SomethingWentWrongTitle
import br.com.ladyplant.ui.components.TopBar

@Composable
fun SomethingWentWrongScreen(navController: NavHostController) {
    TopBar {
        navController.popBackStack()
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_something_went_wrong_background),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            SomethingWentWrongTitle("Ops... seems like we made a mess!")
            SomethingWentWrongSubTitle("Please try again while we clean this up")
        }
    }
}
