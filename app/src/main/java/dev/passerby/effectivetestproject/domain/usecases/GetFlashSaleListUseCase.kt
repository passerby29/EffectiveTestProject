package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.FlashSaleItems
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response
@Suppress("unused")
class GetFlashSaleListUseCase(private val repository: MainRepository) {
    suspend fun getFlashSaleList(): Response<FlashSaleItems>? {
        return repository.getFlashSaleItemsList()
    }
}