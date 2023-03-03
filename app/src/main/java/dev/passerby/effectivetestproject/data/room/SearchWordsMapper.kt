package dev.passerby.effectivetestproject.data.room

import dev.passerby.effectivetestproject.domain.models.SearchWords

class SearchWordsMapper {

    fun mapEntityToDbModel(searchWords: SearchWords, position: Int) = SearchWordsEntity(
        words = searchWords.words[position]
    )
}