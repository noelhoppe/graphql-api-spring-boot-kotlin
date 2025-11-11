package de.thm.mni.graphql.order.api.graphql

import de.thm.mni.graphql.item.api.graphql.dto.ItemInput
import de.thm.mni.graphql.item.api.graphql.dto.toItemModel
import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.order.api.graphql.dto.OrderInput
import de.thm.mni.graphql.order.api.graphql.dto.OrderInputPartial
import de.thm.mni.graphql.order.api.graphql.dto.toOrderModel
import de.thm.mni.graphql.order.model.toOrder
import de.thm.mni.graphql.order.model.updateWith
import de.thm.mni.graphql.order.service.OrderService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class OrderController(
    private val orderService: OrderService,
    private val itemService: ItemService,
) {
    @QueryMapping
    fun findOrderByID(
        @Argument id: String
    ): Order {
        return this.orderService.getOrder(id).toOrder()
    }

    @QueryMapping
    fun findAllOrders(): List<Order> {
        return this.orderService.getOrders().map { it.toOrder() }
    }

    @MutationMapping
    fun createOrderForUser(
        @Argument userID: String,
        @Argument orderInput: OrderInput,
        @Argument items: List<ItemInput>
    ): Order {
        val createdOrder =
            this.orderService.createOrder(orderInput.toOrderModel(UUID.randomUUID().toString(), userID))
        items.forEach { itemInput ->
            this.itemService.createItem(itemInput.toItemModel(UUID.randomUUID().toString(), createdOrder.id))
        }
        return createdOrder.toOrder()
    }

    @MutationMapping
    fun updateOrder(
        @Argument id: String,
        @Argument orderInput: OrderInput
    ): Order {
        val foundOrderModel = this.orderService.getOrder(id)
        val updatedOrderModel = foundOrderModel.updateWith(orderInput)
        return this.orderService.updateOrder(updatedOrderModel).toOrder()
    }

    @MutationMapping
    fun updateOrderPartial(
        @Argument id: String,
        @Argument orderInputPartial: OrderInputPartial
    ): Order {
        val foundOrderModel = this.orderService.getOrder(id)
        val updatedOrderModel = foundOrderModel.updateWith(orderInputPartial)
        return this.orderService.updateOrder(updatedOrderModel).toOrder()
    }

    @MutationMapping
    fun deleteOrder(
        @Argument id: String
    ) = this.orderService.deleteOrder(id)
}