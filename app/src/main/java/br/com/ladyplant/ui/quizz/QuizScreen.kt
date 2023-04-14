package br.com.ladyplant.ui.quizz

import android.net.Uri
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.ladyplant.R
import br.com.ladyplant.ui.components.Quiz
import br.com.ladyplant.ui.components.QuizPage
import br.com.ladyplant.ui.components.ResultListShimmer
import br.com.ladyplant.ui.components.TopBar
import br.com.ladyplant.ui.navigation.NavItem
import br.com.ladyplant.ui.navigation.PlantList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val currentPage = mutableStateOf("1")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    navController: NavController,
    backStackEntry: NavBackStackEntry,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val items = getUIListFromModel(viewModel, coroutineScope, pagerState, navController)
    var showShimmer by remember { mutableStateOf(false) }
    overwriteOnBackPressed(coroutineScope, pagerState, navController)

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        viewModel.viewState.loading.observe(
            lifecycleOwner
        ) { loading ->
            showShimmer = loading
        }

        viewModel.viewState.action.observe(
            lifecycleOwner
        ) { action ->
            when (action) {
                is QuizViewState.Action.GoToResultList -> {
                    goToResultScreen(action, navController)
                }
                is QuizViewState.Action.ShowError -> TODO()
            }
        }
    }
    if (showShimmer) ResultListShimmer()
    else {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(items, navController, coroutineScope, pagerState)
            Quiz(
                item = items, pagerState = pagerState, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun goToResultScreen(
    action: QuizViewState.Action.GoToResultList, navController: NavController
) {
    val list = PlantList(action.resultList)
    val resultListArg = Uri.encode(Json.encodeToString(list))
    navController.navigate(
        "result/$resultListArg",
        NavOptions.Builder().setPopUpTo(NavItem.Quiz.screen_route, true).build()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopBar(
    items: ArrayList<@Composable () -> Unit>,
    navController: NavController,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    TopBar(title = {
        Text(
            text = "question ${currentPage.value} / ${items.size}",
            maxLines = 1,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
    }, actions = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.Close, contentDescription = "Fechar")
        }
    }) {
        coroutineScope.launch {
            back(pagerState, navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun getUIListFromModel(
    viewModel: QuizViewModel,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    navController: NavController
): ArrayList<@Composable () -> Unit> {
    val items = ArrayList<@Composable () -> Unit>()
    viewModel.getQuestions().forEach { question ->
        items.add {
            QuizPage(question, onClick = { ans ->
                coroutineScope.launch {
                    viewModel.setSelectedAnswer(question.id, ans)
                    if (pagerState.currentPage == items.size - 1) {
                        viewModel.dispatchViewAction(QuizViewAction.GetQuizResult)
                    } else {
                        val pageCount = pagerState.currentPage + 2
                        currentPage.value = "$pageCount"
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            })
        }
    }
    return items
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun overwriteOnBackPressed(
    coroutineScope: CoroutineScope, pagerState: PagerState, navController: NavController
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                coroutineScope.launch {
                    back(pagerState, navController)
                }
            }
        }
    }
    DisposableEffect(key1 = onBackPressedDispatcher) {
        onBackPressedDispatcher?.addCallback(callback)
        onDispose {
            currentPage.value = "1"
            callback.remove()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private suspend fun back(
    pagerState: PagerState, navController: NavController
) {
    if (pagerState.currentPage > 0) {
        val previousPage = pagerState.currentPage
        currentPage.value = "$previousPage"
        pagerState.animateScrollToPage(previousPage - 1)
    } else {
        navController.popBackStack()
    }
}