package dev.passerby.effectivetestproject.domain.repos

import dev.passerby.effectivetestproject.domain.models.*
import retrofit2.Response

interface MainRepository {

    suspend fun getSelectedItem(): Response<SelectedItem>?

    suspend fun getFlashSaleItemsList(): Response<FlashSaleItems>?

    suspend fun getLatestItemsList(): Response<LatestItems>?

    suspend fun getSearchWords(): Response<SearchWords>?
}