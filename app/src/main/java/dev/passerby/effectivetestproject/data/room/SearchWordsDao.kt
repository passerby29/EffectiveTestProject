package dev.passerby.effectivetestproject.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SearchWordsDao {

    @Query("select * from SearchWords where words like :filter")
    fun getSearchWordsByFilter(filter: String): LiveData<List<SearchWordsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordsEntity: SearchWordsEntity)
}