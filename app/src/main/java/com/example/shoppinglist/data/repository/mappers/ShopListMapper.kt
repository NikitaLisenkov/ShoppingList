package com.example.shoppinglist.data.repository.mappers

import com.example.shoppinglist.data.database.entity.ShopItemEntity
import com.example.shoppinglist.domain.model.ShopItem

class ShopListMapper {
    fun mapDomainToEntity(shopItem: ShopItem): ShopItemEntity {
        return ShopItemEntity(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            isEnabled = shopItem.isEnabled
        )
    }

    fun mapEntityToDomain(shopItemEntity: ShopItemEntity): ShopItem {
        return ShopItem(
            id = shopItemEntity.id,
            name = shopItemEntity.name,
            count = shopItemEntity.count,
            isEnabled = shopItemEntity.isEnabled
        )
    }

    fun mapListEntityToListDomain(list: List<ShopItemEntity>): List<ShopItem> {
        return list.map { mapEntityToDomain(it) }
    }
}