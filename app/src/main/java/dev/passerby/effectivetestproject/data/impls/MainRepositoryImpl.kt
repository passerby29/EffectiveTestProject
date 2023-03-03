package dev.passerby.effectivetestproject.data.impls

import android.app.Application
import dev.passerby.effectivetestproject.data.room.EffectiveAppDB
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.data.room.SearchWordsMapper
import dev.passerby.effectivetestproject.data.server.APIs
import dev.passerby.effectivetestproject.domain.models.FlashSaleItems
import dev.passerby.effectivetestproject.domain.models.LatestItems
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.models.SelectedItem
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response

class MainRepositoryImpl(application: Application) : MainRepository {

    private val searchWordsDao = EffectiveAppDB.getDB(application).getSearchWordsDao()

    private val api = APIs.getApi()

    override suspend fun getSelectedItem(): Response<SelectedItem>? {
        return api?.getSelectedItem()
    }

    override suspend fun getFlashSaleItemsList(): Response<FlashSaleItems>? {
        return api?.getFlashSaleItemsList()
    }

    override suspend fun getLatestItemsList(): Response<LatestItems>? {
        return api?.getLatestItemsList()
    }

    override suspend fun getSearchWords(): Response<SearchWords>? {
        return api?.getSearchWords()
    }

    override suspend fun addSearchWordsToDB(searchWord: SearchWordsEntity) {
        searchWordsDao.insert(searchWord)
    }

    override suspend fun getSearchWordsFromDB(filter: String): List<SearchWordsEntity> {
        return searchWordsDao.getSearchWordsByFilter(filter)
    }
}