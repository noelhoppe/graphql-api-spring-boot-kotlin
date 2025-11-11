package de.thm.mni.graphql.item.repository

import de.thm.mni.graphql.item.model.ItemModel
import de.thm.mni.graphql.item.model.items

object ItemRepository {
    fun getItemByID(id: String) = items.find { it.id == id }
    fun save(itemModel: ItemModel): ItemModel {
        items.add(itemModel)
        return itemModel
    }
    fun deleteByID(id: String): Boolean {
        return items.removeIf { it.id == id }
    }

    fun findByOrderID(orderID: String) = items.filter { it.orderID == orderID }
    fun findByProductID(id: String) = items.filter { it.productID == id }
    fun findByID(id: String) = items.find { it.id == id }
}
