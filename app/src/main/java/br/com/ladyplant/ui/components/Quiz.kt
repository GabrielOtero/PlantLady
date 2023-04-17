package br.com.ladyplant.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ladyplant.domain.model.Question

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Quiz(
    item: List<@Composable () -> Unit>, pagerState: PagerState, modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(
                state = pagerState, userScrollEnabled = false, pageCount = item.size
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item[page].invoke()
                }
            }
        }
    }
}

@Composable
fun QuizPage(question: Question, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(Modifier.padding(vertical = 28.dp))
        QuizQuestionText(text = question.title)
        Spacer(Modifier.padding(vertical = 28.dp))
        question.options.forEachIndexed { index, text ->
            val modifier = Modifier.padding(vertical = 12.dp)

            QuizOptionButton(
                text = text, onClick = {
                    onClick(index)
                }, modifier = modifier
            )
        }
    }
}