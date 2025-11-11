package de.thm.mni.graphql.user.api.graphql

import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.order.model.toOrder
import de.thm.mni.graphql.order.service.OrderService
import de.thm.mni.graphql.user.api.graphql.dtos.User
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserFieldResolver(
    private val orderService: OrderService
) {
    @SchemaMapping(typeName = "User", field = "orders")
    fun orders(user: User): List<Order> {
        return this.orderService.getOrdersByUserID(user.id).map { it.toOrder() }
    }
}