package dev.passerby.effectivetestproject.data.impls

import dev.passerby.effectivetestproject.data.server.APIs
import dev.passerby.effectivetestproject.domain.models.*
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response

@Suppress("unused")
object MainRepositoryImpl: MainRepository {

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
}