package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}