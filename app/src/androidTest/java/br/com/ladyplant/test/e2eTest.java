package br.com.ladyplant.test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ladyplant.R;
import br.com.ladyplant.domain.model.Constants;
import br.com.ladyplant.test.stepdefinitions.DescriptionSteps;
import br.com.ladyplant.test.stepdefinitions.StepDefinition;
import br.com.ladyplant.view.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static br.com.ladyplant.test.screens.ExploreScreen.*;
import static br.com.ladyplant.test.screens.MainScreen.*;
import static br.com.ladyplant.test.screens.QuizScreen.*;
import static br.com.ladyplant.test.screens.ResultScreen.*;
import static br.com.ladyplant.test.screens.ResultScreen.resultTitle;

@RunWith(AndroidJUnit4.class)
public class e2eTest extends StepDefinition {

    private DescriptionSteps descriptionSteps = new DescriptionSteps();

    private CountingIdlingResource myIdlingResource = new CountingIdlingResource(Constants.IDLE_RESOURCE_NAME);

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void before() {
        IdlingRegistry.getInstance().register(myIdlingResource);
    }

    @After
    public void after() {
        IdlingRegistry.getInstance().unregister(myIdlingResource);
    }

    @Test
    public void CompleteQuizWithTropical(){
        //check elements of main screen
        checkIdIsDisplayed(logoMainScreen);
        checkTextIsDisplayedOnId(findBtn, subTitleTxt);
        checkTextIsDisplayedOnId(exploreBtn, exploreBtnTxt);

        //click on element find it now
        clickOnElementId(quizBtn);
        waitElement(1000);

        //validate first page
        checkTextIsDisplayed(firstQuestionStg);
        checkTextIsDisplayedOnId(questionCount, firstCountStg);
        checkTextIsDisplayed(tropicalStg);
        checkTextIsDisplayed(coldStg);
        checkTextIsDisplayed(dryStg);
        checkTextIsDisplayed(mildStg);
        checkIdIsDisplayed(backBtn);

        //go to second page clicking on tropical
        clickOnElementWithText(tropicalStg);
        waitElement(500);

        //validate second page
        checkTextIsDisplayed(secondQuestionStg);
        checkTextIsDisplayedOnId(questionCount, secondCountStg);
        checkTextIsDisplayed(iCantKeepDirtAliveStg);
        checkTextIsDisplayed(somewhereCloseToGoodStg);
        checkTextIsDisplayed(iDefinitelyHaveAGreenThumbStg);
        checkIdIsDisplayed(backBtn);

        //go to third page clicking on I can't keep dirt alive
        clickOnElementWithText(iCantKeepDirtAliveStg);
        waitElement(500);

        //validate third page
        checkTextIsDisplayed(whatKindOfLightStg);
        checkTextIsDisplayedOnId(questionCount, thirdCountStg);
        checkTextIsDisplayed(directSunlightStg);
        checkTextIsDisplayed(theresBrightnessStg);
        checkTextIsDisplayed(onlyArtificialLightsStg);
        checkIdIsDisplayed(backBtn);

        //go to fourth page clicking on Direct sunlight
        clickOnElementWithText(directSunlightStg);
        waitElement(500);

        //validate fourth page
        checkTextIsDisplayed(whatAreYouLookingStg);
        checkTextIsDisplayedOnId(questionCount, fourthQuestionStg);
        checkTextIsDisplayed(somethingColorfulStg);
        checkTextIsDisplayed(somethingWithInterestingLeafsStg);
        checkTextIsDisplayed(iDontReallyMindStg);
        checkIdIsDisplayed(backBtn);

        //go to fifth page clicking on Something colorful
        waitElement(500);
        clickOnElementWithText(somethingColorfulStg);
        waitElement(500);

        //validate fifth page
        checkTextIsDisplayed(areYouThinkingOfAGroundPlant);
        checkTextIsDisplayedOnId(questionCount, fifthQuestionStg);
        checkTextIsDisplayed(groundPlantStg);
        checkTextIsDisplayed(hangingPlantStg);
        checkIdIsDisplayed(backBtn);

        //go to sixth page clicking on Ground plant
        clickOnElementWithText(groundPlantStg);
        waitElement(500);

        //validate sixth page
        checkTextIsDisplayed(areYouLookingForJustStg);
        checkTextIsDisplayedOnId(questionCount, sixthCountStg);
        checkTextIsDisplayed(itsMoreAboutAestheticsStg);
        checkTextIsDisplayed(somethingForCookingStg);
        checkIdIsDisplayed(backBtn);

        //go to last page clicking on It's more about aesthetics
        clickOnElementWithText(itsMoreAboutAestheticsStg);
        waitElement(500);

        //validate last page
        waitElement(500);
        checkTextIsDisplayed(thisOneIsVeryImportantStg);
        checkTextIsDisplayedOnId(questionCount, seventhQuestionStg);
        checkTextIsDisplayed(yesThatsARiskStg);
        checkTextIsDisplayed(nopeItsAllGoodStg);
        checkIdIsDisplayed(backBtn);

        //go to results
        clickOnElementWithText(yesThatsARiskStg);
        waitElement(2000);

        //validate results page
        checkTextIsDisplayedOnId(resultTitle, resultsStg);
        checkTextIsDisplayed(hereAreSomeOptionsForYouStg);
        checkTextIsDisplayed(plasticPlantWithCapitalLetterStg);
        checkTextIsDisplayed(orTakeTheQuizAgainBtnStg);

        //go to plastic plant
        clickOnElementWithText(plasticPlantWithCapitalLetterStg);

        //validate results page
        descriptionSteps.checkAllElementsOnDescription(plasticPlantStg, polyesterArtificialysStg, chinaStg, noStg, everyTypeOfLightStg, noNeedToWaterStg, sometimesThePlantYoureLookingForIsJustStg);
    }

    @Test
    public void SelectSpecificPlantOnResultList() {
        onView(withId(R.id.quizz_btn)).perform(click());

        onView(withText(tropicalStg)).perform(click());
        waitElement(500);

        onView(withText(somewhereCloseToGoodStg)).perform(click());
        waitElement(500);

        onView(withText(theresBrightnessStg)).perform(click());
        waitElement(500);

        onView(withText(iDontReallyMindStg)).perform(click());
        waitElement(500);

        onView(withText(groundPlantStg)).perform(click());
        waitElement(500);

        onView(withText(itsMoreAboutAestheticsStg)).perform(click());
        waitElement(500);

        onView(withText(nopeItsAllGoodStg)).perform(click());
        waitElement(2000);

        scrollAndClickOnElementWithTextOnRecyclerView(resultListId, descriptionId, begoniaCapitalLetterStg);
        descriptionSteps.checkAllElementsOnDescription(begoniaStg, begoniaScientificNameStg, brazilStg, yesStg, indirectSunlightStg, onceAWeekStg, begoniasAreVeryStg);
    }

    @Test
    public void ExploreSelectingPlantAfterSearch() {
        onView(withId(exploreBtn)).perform(click());

        checkTextIsDisplayedOnId(titleId,exploreTitleStg);
        checkTextIsDisplayedOnId(subtitleId,andFindYourNextPlanStg);

        writeOnElement(searchPlantId, begoniaStg);
        pressImeBtn(searchPlantId);

        waitElement(2000);
        checkTextIsDisplayedOnId(resultTitleId, exploreTitleStg);
        checkTextIsDisplayedOnId(resultSubtitleId, hereAreResultsForBegoniaStg);
        scrollAndClickOnElementWithTextOnRecyclerView(resultListId, descriptionId,begoniaCapitalLetterStg);
        descriptionSteps.checkAllElementsOnDescription(begoniaStg, begoniaScientificNameStg, brazilStg, yesStg, indirectSunlightStg, onceAWeekStg, begoniasAreVeryStg);
    }

    @Test
    public void ValidateBegoniaDetailsBySelectingTypeOfFlower() {
        onView(withId(exploreBtn)).perform(click());

        swipeRepeatedlyUntilText("swipeleft", descId, byTypeListId,flowerStg);
        clickOnElementWithText(flowerStg);

        waitElement(1000);
        clickOnElementWithText(begoniaCapitalLetterStg);

        waitElement(2000);
        descriptionSteps.checkAllElementsOnDescription(begoniaStg, begoniaScientificNameStg, brazilStg, yesStg, indirectSunlightStg, onceAWeekStg, begoniasAreVeryStg);
    }
}