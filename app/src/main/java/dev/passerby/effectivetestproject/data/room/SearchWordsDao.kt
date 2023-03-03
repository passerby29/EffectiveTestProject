package dev.passerby.effectivetestproject.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

interface SearchWordsDao {

    @Query("select * from SearchWords where words like :char")
    suspend fun getSearchWordsByFilter(char: String): LiveData<List<SearchWordsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordsEntity: SearchWordsEntity)
}