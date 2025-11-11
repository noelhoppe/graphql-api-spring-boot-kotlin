package de.thm.mni.graphql.order.repository

import de.thm.mni.graphql.item.repository.ItemRepository
import de.thm.mni.graphql.order.model.OrderModel
import de.thm.mni.graphql.order.model.orders

object OrderRepository {
    fun save(orderModel: OrderModel): OrderModel {
        orders.add(orderModel)
        return orderModel
    }
    fun findAll() = orders
    fun findByID(id: String) = orders.find { it.id == id }
    fun findByUserID(userID: String): List<OrderModel> = orders.filter { it.userID == userID }
    fun deleteByID(id: String): Boolean {
        ItemRepository.findByOrderID(id).forEach { itemModel ->
            ItemRepository.deleteByID(itemModel.id)
        }
        return orders.removeIf { it.id == id }
    }
}