package br.com.ladyplant.ui.quizz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.ladyplant.domain.model.Question
import br.com.ladyplant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    override val viewState: QuizViewState,
) : BaseViewModel<QuizViewState, QuizViewAction>() {

    fun getQuestions(): List<Question> {
        return questionList
    }

    fun setSelectedAnswer(i: Int, ans: Int) {
        questionList.find { it.id == i }?.answer = ans
    }

    override fun dispatchViewAction(viewAction: QuizViewAction) {
        viewModelScope.launch {
            when (viewAction) {
                is QuizViewAction.GetQuizResult -> {
                    viewState.loading.postValue(true)
                    Log.d("@@@", questionList.toString())
                }
            }
        }
    }

    companion object {
        val questionList = listOf(
            Question(
                1, "Let’s begin! What kind of climate are you in?", listOf(
                    "Tropical", "Cold", "Dry", "Mild"
                )
            ),
            Question(
                2, "What are your gardening skills? Be honest, there's no judgment here.", listOf(
                    "I can't keep dirt alive",
                    "Somewhere close to good",
                    "I definitely have a green thumb"
                )
            ),
            Question(
                3, "What kind of light is there where you want to put your plant?", listOf(
                    "Direct sunlight",
                    "There's brightness but no direct sunlight",
                    "Only artificial lights or a little brightness from the sun"
                )
            ),
            Question(
                4, "What are you looking for in your plant?", listOf(
                    "Something colorful", "Something with interesting leafs", "I don’t really mind"
                )
            ),
            Question(
                5,
                "Are you thinking of a ground plant to put on a vase, or a hanging plant to stay high?",
                listOf(
                    "Ground plant", "Hanging plant"
                )
            ),
            Question(
                6, "Are you looking for just a pretty plant or something with a purpose?", listOf(
                    "It's more about aesthetics",
                    "Something for cooking or with medicinal properties"
                )
            ),
            Question(
                7,
                "This one is very important. o you have a small kid or pet that might eat the plant?",
                listOf(
                    "Yes, that’s a risk", "Nope, it’s all good"
                )
            ),
        )
    }
}