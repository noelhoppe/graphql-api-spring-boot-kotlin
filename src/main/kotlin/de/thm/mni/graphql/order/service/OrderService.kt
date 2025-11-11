package de.thm.mni.graphql.order.service

import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.order.model.OrderModel
import de.thm.mni.graphql.order.repository.OrderRepository
import de.thm.mni.graphql.user.service.UserService
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val userService: UserService,
    private val itemService: ItemService
) {
    fun getOrders() = OrderRepository.findAll()

    fun getOrdersByUserID(userID: String) = OrderRepository.findByUserID(userID)

    fun getOrder(id: String): OrderModel {
        return OrderRepository.findByID(id) ?: throw NoSuchElementException("No order with ID $id")
    }

    fun createOrder(
        orderModel: OrderModel
    ): OrderModel {
        this.userService.getUser(orderModel.userID)
        return OrderRepository.save(orderModel)
    }

    fun updateOrder(
        updatedOrderModel: OrderModel
    ): OrderModel {
        val foundOrderModel = this.getOrder(updatedOrderModel.id)
        foundOrderModel.apply {
            date = updatedOrderModel.date
            state = updatedOrderModel.state
        }
        return foundOrderModel
    }

    fun deleteOrder(id: String): Boolean {
        val foundOrderModel = this.getOrder(id)
        return OrderRepository.deleteByID(foundOrderModel.id)
    }
}