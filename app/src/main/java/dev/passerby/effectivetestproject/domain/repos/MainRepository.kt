package dev.passerby.effectivetestproject.domain.repos

import dev.passerby.effectivetestproject.domain.models.*

interface MainRepository {

    fun getSelectedItem(): SelectedItem

    fun getFlashSaleItemsList(): FlashSaleItems

    fun getLatestItemsList(): LatestItems

    fun getSearchWords(): SearchWords
}