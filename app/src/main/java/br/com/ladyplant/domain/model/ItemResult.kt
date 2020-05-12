package br.com.ladyplant.domain.model

open class ItemResult(val id : Int = 0, val description: String? = null, val image : String? = null, val resultType: ResultType = ResultType.PLANT)

class TakeQuizAgainResult(val title: String) :
    ItemResult(0,null, null, ResultType.TAKE_QUIZ_AGAIN)

class HeaderResult(val title: String, val subtitle : String) :
    ItemResult(0,null, null, ResultType.HEADER)
