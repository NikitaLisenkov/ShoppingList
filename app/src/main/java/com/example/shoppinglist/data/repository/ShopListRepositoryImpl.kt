package com.example.shoppinglist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shoppinglist.data.database.AppDatabase
import com.example.shoppinglist.data.repository.mappers.ShopListMapper
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val dao = AppDatabase.getInstance(application).shopListDao()

    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        dao.addShopItem(mapper.mapDomainToEntity(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        dao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        dao.addShopItem(mapper.mapDomainToEntity(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val entity = dao.getShopItem(shopItemId)
        return mapper.mapEntityToDomain(entity)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        MediatorLiveData<List<ShopItem>>().apply {
            addSource(dao.getShopList()) { list ->
                value = mapper.mapListEntityToListDomain(list)
            }
        }
}