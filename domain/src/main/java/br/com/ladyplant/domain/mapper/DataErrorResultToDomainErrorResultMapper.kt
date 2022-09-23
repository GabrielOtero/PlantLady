package br.com.ladyplant.domain.mapper

import br.com.ladyplant.domain.model.DomainErrorResult
import br.com.ladyplant.repository.utils.DataErrorResult


class DataErrorResultToDomainErrorResultMapper : BaseMapper<DataErrorResult, DomainErrorResult>() {
    override fun mapFrom(from: DataErrorResult) = DomainErrorResult(
        message = from.exceptionMessage
    )
}