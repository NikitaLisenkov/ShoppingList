package com.example.shoppinglist.presentation.rv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.presentation.rv.viewholders.ShopItemViewHolder

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isEnabled) {
            SHOP_ITEM_ENABLED_VIEW_TYPE
        } else {
            SHOP_ITEM_DISABLED_VIEW_TYPE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            SHOP_ITEM_ENABLED_VIEW_TYPE -> R.layout.item_shop_enabled
            SHOP_ITEM_DISABLED_VIEW_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException(UNKNOWN_VIEW_TYPE)
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(
                layout,
                parent,
                false
            )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        with(holder) {
            view.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            view.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    companion object {
        const val SHOP_ITEM_ENABLED_VIEW_TYPE = 1
        const val SHOP_ITEM_DISABLED_VIEW_TYPE = 2
        const val UNKNOWN_VIEW_TYPE = "UNKNOWN_VIEW_TYPE"

        const val MAX_POOL_SIZE = 15
    }
}





