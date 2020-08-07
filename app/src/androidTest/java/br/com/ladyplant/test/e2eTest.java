package br.com.ladyplant.test;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.ladyplant.R;
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

public class e2eTest extends StepDefinition {

    private DescriptionSteps descriptionSteps = new DescriptionSteps();

    @Rule
    public ActivityTestRule<MainActivity>
            mActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void CompleteQuizWithTropical(){
        //check elements of main screen
        checkIdIsDisplayed(logoMainScreen);
        checkTextIsDisplayedOnId(findBtn, subTitleTxt);
        checkTextIsDisplayedOnId(exploreBtn, exploreBtnTxt);

        //click on element find it now
        waitElement(1);
        clickOnElementId(quizBtn);

        //validate first page
        waitElement(1);
        checkTextIsDisplayed(firstQuestionStg);
        checkTextIsDisplayedOnId(questionCount, firstCountStg);
        checkTextIsDisplayed(tropicalStg);
        checkTextIsDisplayed(coldStg);
        checkTextIsDisplayed(dryStg);
        checkTextIsDisplayed(mildStg);
        checkIdIsDisplayed(backBtn);

        //go to second page clicking on tropical
        waitElement(500);
        clickOnElementWithText(tropicalStg);

        //validate second page
        waitElement(500);
        checkTextIsDisplayed(secondQuestionStg);
        checkTextIsDisplayedOnId(questionCount, secondCountStg);
        checkTextIsDisplayed(iCantKeepDirtAliveStg);
        checkTextIsDisplayed(somewhereCloseToGoodStg);
        checkTextIsDisplayed(iDefinetelyHaveAGreenThumbStg);
        checkIdIsDisplayed(backBtn);

        //go to third page clicking on I can't keep dirt alive
        waitElement(500);
        clickOnElementWithText(iCantKeepDirtAliveStg);

        //validate third page
        waitElement(500);
        checkTextIsDisplayed(whatKindOfLightStg);
        checkTextIsDisplayedOnId(questionCount, thirdCountStg);
        checkTextIsDisplayed(directSunlightStg);
        checkTextIsDisplayed(theresBrightnessStg);
        checkTextIsDisplayed(onlyArtificialLightsStg);
        checkIdIsDisplayed(backBtn);

        //go to fourth page clicking on Direct sunlight
        waitElement(500);
        clickOnElementWithText(directSunlightStg);

        //validate fourth page
        waitElement(500);
        checkTextIsDisplayed(whatAreYouLookingStg);
        checkTextIsDisplayedOnId(questionCount, fourthQuestionStg);
        checkTextIsDisplayed(somethingColorfulStg);
        checkTextIsDisplayed(somethingWithInterestingLeafsStg);
        checkTextIsDisplayed(iDontReallyMindStg);
        checkIdIsDisplayed(backBtn);

        //go to fifth page clicking on Something colorful
        waitElement(500);
        clickOnElementWithText(somethingColorfulStg);

        //validate fifth page
        waitElement(500);
        checkTextIsDisplayed(areYouThinkingOfAGroundPlant);
        checkTextIsDisplayedOnId(questionCount, fifthQuestionStg);
        checkTextIsDisplayed(groundPlantStg);
        checkTextIsDisplayed(hangingPlantStg);
        checkIdIsDisplayed(backBtn);

        //go to sixth page clicking on Ground plant
        waitElement(500);
        clickOnElementWithText(groundPlantStg);

        //validate sixth page
        waitElement(500);
        checkTextIsDisplayed(areYouLookingForJustStg);
        checkTextIsDisplayedOnId(questionCount, sixthCountStg);
        checkTextIsDisplayed(itsMoreAboutAestheticsStg);
        checkTextIsDisplayed(somethingForCookingStg);
        checkIdIsDisplayed(backBtn);

        //go to last page clicking on It's more about aesthetics
        waitElement(500);
        clickOnElementWithText(itsMoreAboutAestheticsStg);

        //validate last page
        waitElement(500);
        checkTextIsDisplayed(thisOneIsVeryImportantStg);
        checkTextIsDisplayedOnId(questionCount, seventhQuestionStg);
        checkTextIsDisplayed(yesThatsARiskStg);
        checkTextIsDisplayed(nopeItsAllGoodStg);
        checkIdIsDisplayed(backBtn);

        //go to results
        waitElement(500);
        clickOnElementWithText(yesThatsARiskStg);

        //validate results page
        waitElement(3000);
        checkTextIsDisplayedOnId(resultTitle, resultsStg);
        checkTextIsDisplayed(hereAreSomeOptionsForYouStg);
        checkTextIsDisplayed(plasticPlantWithCapitalLetterStg);
        checkTextIsDisplayed(orTakeTheQuizAgainBtnStg);

        //go to plastic plant
        waitElement(500);
        clickOnElementWithText(plasticPlantWithCapitalLetterStg);

        //validate results page
        waitElement(500);
        descriptionSteps.checkAllElementsOnDescription(plasticPlantStg, polyesterArtificialysStg, chinaStg, noStg, everyTypeOfLightStg, noNeedToWaterStg, sometimesThePlantYoureLookingForIsJustStg);
    }

    @Test
    public void SelectSpecificPlantOnResultList() {
        onView(withId(R.id.quizz_btn)).perform(click());
        waitElement(500);

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
        waitElement(500);

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

        waitElement(500);
        swipeRepeatedlyUntilText("swipeleft",descId, byTypeListId,flowerStg);
        clickOnElementWithText(flowerStg);

        waitElement(1000);
        clickOnElementWithText(begoniaCapitalLetterStg);

        waitElement(2000);
        descriptionSteps.checkAllElementsOnDescription(begoniaStg, begoniaScientificNameStg, brazilStg, yesStg, indirectSunlightStg, onceAWeekStg, begoniasAreVeryStg);
    }
}