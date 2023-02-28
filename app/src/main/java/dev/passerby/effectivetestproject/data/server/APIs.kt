package dev.passerby.effectivetestproject.data.server

import dev.passerby.effectivetestproject.domain.models.FlashSaleItems
import dev.passerby.effectivetestproject.domain.models.LatestItems
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.models.SelectedItem
import retrofit2.Response
import retrofit2.http.GET

interface APIs {

    @GET("4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchWords(): Response<SearchWords>

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestItemsList(): Response<LatestItems>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleItemsList(): Response<FlashSaleItems>

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getSelectedItem(): Response<SelectedItem>

    companion object {
        fun getApi(): APIs? {
            return ApiClient.client?.create(APIs::class.java)
        }
    }
}