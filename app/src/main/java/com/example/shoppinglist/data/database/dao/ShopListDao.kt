package com.example.shoppinglist.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.data.database.entity.ShopItemEntity

@Dao
interface ShopListDao {
    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemEntity: ShopItemEntity)
    // этот метод также редактирует элемент, т.к если добавить элемент с существующим id, то он перезапишет его

    @Query("DELETE FROM shop_items WHERE id =:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id =:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): ShopItemEntity
}