package br.com.ladyplant.test.stepdefinitions;

import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.repeatedlyUntil;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class StepDefinition {

    public void checkIdIsDisplayed(int elementId) {
        onView(withId(elementId)).check(matches(isDisplayed()));
    }

    public void checkTextIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public void checkTextIsDisplayedOnId(int elementId, String text) {
        onView(withId(elementId)).check(matches(withText(text)));
    }

    public void clickOnElementId(int elementId) {
        onView(withId(elementId)).perform(click());
    }

    public void clickOnElementWithText(String text) {
        onView(withText(text)).perform(click());
    }

    public void scrollToElementWithId(int elementId) {
        onView(withId(elementId)).perform(ViewActions.scrollTo());
    }

    public void scrollToElementWithText(String text) {
        onView(withText(text)).perform(ViewActions.scrollTo());
    }

    public void scrollToElementAtPosition(int recyclerViewId, int position) {
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(position));
    }

    public void scrollAndClickOnElementWithTextOnRecyclerView(int recyclerViewId, int elementId, String text) {
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.actionOnItem(hasDescendant(allOf(withId(elementId), withText(text))), click()));
    }

    public void swipeOnView(int viewId, String swipe) {
        switch(swipe) {
            case "swipeup": onView(withId(viewId)).perform(ViewActions.swipeUp());
            case "swipedown": onView(withId(viewId)).perform(ViewActions.swipeDown());
            case "swipeleft": onView(withId(viewId)).perform(ViewActions.swipeLeft());
            case "swiperight": onView(withId(viewId)).perform(ViewActions.swipeRight());
        }
    }

    public void swipeRepeatedlyUntilText(String swipe, int elementId, int recyclerView, String text) {
        switch(swipe) {
            case "swipeup": onView(withId(recyclerView)).perform(repeatedlyUntil(ViewActions.swipeUp(),hasDescendant(allOf(withId(elementId), withText(text))), 5));
            case "swipedown": onView(withId(recyclerView)).perform(repeatedlyUntil(ViewActions.swipeDown(),hasDescendant(allOf(withId(elementId), withText(text))), 5));
            case "swipeleft": onView(withId(recyclerView)).perform(repeatedlyUntil(ViewActions.swipeLeft(),hasDescendant(allOf(withId(elementId), withText(text))), 5));
            case "swiperight": onView(withId(recyclerView)).perform(repeatedlyUntil(ViewActions.swipeRight(),hasDescendant(allOf(withId(elementId), withText(text))), 5));
        }
    }

    public void waitElement(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeOnElement(int elementId, String text) {
        onView(withId(elementId)).perform(typeText(text));
    }

    public void pressImeBtn(int elementId) {
        onView(withId(elementId)).perform(pressImeActionButton());
    }

    public static Matcher<View> getElementFromMatchAtPosition(final Matcher<View> matcher, final int position) {
        return new BaseMatcher<View>() {
            int counter = 0;

            @Override
            public boolean matches(final Object item) {
                if (matcher.matches(item)) {
                    if (counter == position) {
                        counter++;
                        return true;
                    }
                    counter++;
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Element at hierarchy position " + position);
            }
        };
    }

    public void checkViewHasTextOnPosition(int viewId, String expected, int position) {
        onView(getElementFromMatchAtPosition(withId(viewId), position)).check(matches((withText(expected))));
    }
}