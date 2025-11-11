package de.thm.mni.graphql.order.api.graphql

import de.thm.mni.graphql.item.api.graphql.dto.Item
import de.thm.mni.graphql.item.model.toItem
import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.order.service.OrderService
import de.thm.mni.graphql.user.api.graphql.dtos.User
import de.thm.mni.graphql.user.model.toUser
import de.thm.mni.graphql.user.service.UserService
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class OrderFieldResolver(
    private val orderService: OrderService,
    private val userService: UserService,
    private val itemService: ItemService
) {
    @SchemaMapping(typeName = "Order", field = "user")
    fun user(order: Order): User {
        return this.userService.getUser(this.orderService.getOrder(order.id).userID).toUser()
    }

    @SchemaMapping(typeName = "Order", field = "items")
    fun items(order: Order): List<Item> {
        return this.itemService.getItemsByOrderID(order.id).map { it.toItem() }
    }
}