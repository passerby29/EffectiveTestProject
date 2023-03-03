package dev.passerby.effectivetestproject.data.room

import androidx.room.*

interface SearchWordsDao {

    @Query("select * from SearchWords where words like :filter")
    suspend fun getSearchWordsByFilter(filter: String): List<SearchWordsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordsEntity: SearchWordsEntity)
}