package de.thm.mni.graphql.item.service

import de.thm.mni.graphql.item.model.ItemModel
import de.thm.mni.graphql.item.repository.ItemRepository
import de.thm.mni.graphql.order.model.OrderModel
import org.springframework.stereotype.Service

@Service
class ItemService {
    fun getItemByID(id: String): ItemModel {
        return ItemRepository.getItemByID(id) ?: throw IllegalArgumentException("Item with id $id not found")
    }

    fun updateItem(updatedItemModel: ItemModel): ItemModel {
        val foundItemModel = this.getItemByID(updatedItemModel.id)
        foundItemModel.apply {
            quantity = updatedItemModel.quantity
        }
        return foundItemModel
    }

    fun getItemsByOrderID(orderID: String) = ItemRepository.findByOrderID(orderID)

    fun getItemsByProductID(id: String) = ItemRepository.findByProductID(id)

    fun createItem(itemModel: ItemModel): ItemModel {
        val foundItemModel = ItemRepository.findByID(itemModel.id)
        if (foundItemModel == null) {
            return ItemRepository.save(itemModel)
        } else {
            throw IllegalArgumentException("Item with id ${itemModel.id} already exists.")
        }
    }

    fun deleteItem(id: String): Boolean {
        val foundItemModel = this.getItemByID(id)
        return ItemRepository.deleteByID(foundItemModel.id)
    }
}