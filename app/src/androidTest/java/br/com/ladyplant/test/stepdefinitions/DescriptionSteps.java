package br.com.ladyplant.test.stepdefinitions;

import static br.com.ladyplant.test.screens.ResultScreen.*;

public class DescriptionSteps extends StepDefinition {

    public void checkAllElementsOnDescription(String type, String scientificName ,String country, String toxic, String light, String water, String overview) {
        checkTextIsDisplayedOnId(yourPlantTitle, yourPlantIsTitleStg);
        checkTextIsDisplayedOnId(plantNameTxt, type);
        checkTextIsDisplayedOnId(scientificNameTitle, scientificName);
        checkIdIsDisplayed(plantImg);
        checkTextIsDisplayedOnId(originTitle, originTitleStg);
        checkTextIsDisplayedOnId(originTxt, country);
        checkTextIsDisplayedOnId(toxicTitle, toxicTitleStg);
        checkTextIsDisplayedOnId(toxicTxt, toxic);
        checkTextIsDisplayedOnId(lightTitle, lightTitleStg);
        checkTextIsDisplayedOnId(lightTxt, light);
        scrollToElementWithId(overviewTitle);
        checkTextIsDisplayedOnId(waterTitle, waterTitleStg);
        checkTextIsDisplayedOnId(waterTxt, water);
        checkTextIsDisplayedOnId(detailTitle, overviewTitleStg);
        checkTextIsDisplayedOnId(overviewTitle, overview);
    }
}