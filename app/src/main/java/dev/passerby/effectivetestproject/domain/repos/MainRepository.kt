package dev.passerby.effectivetestproject.domain.repos

import dev.passerby.effectivetestproject.domain.models.*

interface MainRepository {

    fun getSelectedItem(): SelectedItem

    fun getFlashSaleList(): FlashSaleItems

    fun getLatestList(): LatestItems

    fun getSearchWords(): SearchWords
}