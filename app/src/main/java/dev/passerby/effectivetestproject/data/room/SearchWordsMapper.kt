package dev.passerby.effectivetestproject.data.room

import dev.passerby.effectivetestproject.domain.models.SearchWords

class SearchWordsMapper {

    fun mapListDbModel(list: List<SearchWordsEntity>) = list.map {
        mapDbModelToEntity(it)
    }

    private fun mapDbModelToEntity(searchWordsEntity: SearchWordsEntity) = SearchWords(
        words = searchWordsEntity.words)
}