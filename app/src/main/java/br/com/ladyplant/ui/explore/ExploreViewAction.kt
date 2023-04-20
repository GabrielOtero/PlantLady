package br.com.ladyplant.ui.explore

sealed class ExploreViewAction {
    class GetPlantByType(val idType: Int) : ExploreViewAction()
    class GetPlantByRoom(val idRoom: Int) : ExploreViewAction()
}
