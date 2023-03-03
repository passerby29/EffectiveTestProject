package dev.passerby.effectivetestproject.data.room

import androidx.room.*

@Entity(tableName = "SearchWords", primaryKeys = ["words"])
data class SearchWordsEntity(
    @ColumnInfo val words: String
)
