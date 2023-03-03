package dev.passerby.effectivetestproject.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.domain.models.FlashSaleItems
import dev.passerby.effectivetestproject.domain.models.LatestItems
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.models.SelectedItem
import retrofit2.Response

interface MainRepository {

    suspend fun getSelectedItem(): Response<SelectedItem>?

    suspend fun getFlashSaleItemsList(): Response<FlashSaleItems>?

    suspend fun getLatestItemsList(): Response<LatestItems>?

    suspend fun getSearchWords(): Response<SearchWords>?

    suspend fun addSearchWordsToDB(searchWord: SearchWordsEntity)

    fun getSearchWordsFromDB(filter: String): LiveData<List<SearchWordsEntity>>
}