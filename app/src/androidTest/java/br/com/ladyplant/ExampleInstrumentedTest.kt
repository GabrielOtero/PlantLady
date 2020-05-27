package br.com.ladyplant

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.ItemResult
import br.com.ladyplant.view.MainActivity
import br.com.ladyplant.view.result.ResultViewHolder
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var myIdlingResource: CountingIdlingResource =
        CountingIdlingResource(Constants.IDLE_RESOURCE_NAME)

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun before() {
        IdlingRegistry.getInstance().register(myIdlingResource)
    }

    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(myIdlingResource)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("br.com.ladyplant.qa", appContext.packageName)
    }

    @Test
    fun basicQuizTest() {
        onView(withId(R.id.quizz_btn)).perform(click())

        navigateQuiz()

        onView(Matchers.allOf(withId(R.id.result_title), withText("results"), isDisplayed()))

        onView(
            Matchers.allOf(
                withId(R.id.result_subtitle),
                withText("here are some options for you"),
                isDisplayed()
            )
        )

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.result_list)).perform(actionOnItemAtPosition<ResultViewHolder>(1, click()))

        onView(withText("golden barrel cactus")).check(matches(isDisplayed()))

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.your_plant_image)).check(matches(isCompletelyDisplayed()))

        pressBack()

        onView(withText("Or take the quiz again")).perform(click())

        pressBack()
        pressBack()

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.explore_btn)).perform(click())

        onView(withText("living room")).perform(scrollTo(), click())

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.result_list)).perform(actionOnItemAtPosition<ResultViewHolder>(1, click()))

        onView(withText("golden barrel cactus")).check(matches(isDisplayed()))

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.your_plant_image)).check(matches(isCompletelyDisplayed()))

        pressBack()
        pressBack()

        onView(withText("cactus")).perform(scrollTo(), click())

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.result_list)).perform(actionOnItemAtPosition<ResultViewHolder>(1, click()))

        onView(withText("golden barrel cactus")).check(matches(isDisplayed()))

        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
        onView(withId(R.id.your_plant_image)).check(matches(isCompletelyDisplayed()))

        pressBack()

        onView(withText("Or take the quiz to find your plant")).perform(click())

        navigateQuiz()
    }

    private fun navigateQuiz() {
        onView(withText("Tropical")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("Somewhere close to good")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("There's brightness but no direct sunlight")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("I don’t really mind")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("Ground plant")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("It's more about aesthetics")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())

        onView(withText("Nope, it’s all good")).perform(click())
        Thread.sleep(Constants.QUIZ_TRANSITION_DELAY.toLong())
    }
}
