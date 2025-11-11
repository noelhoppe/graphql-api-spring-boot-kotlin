package de.thm.mni.graphql.order.model

import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.order.api.graphql.dto.OrderInput
import de.thm.mni.graphql.order.api.graphql.dto.OrderInputPartial

data class OrderModel(
    var id: String,
    var date: String,
    var state: String,
    var userID: String
)

val orders = mutableListOf<OrderModel>()

fun OrderModel.updateWith(orderInputPartial: OrderInputPartial): OrderModel {
    return OrderModel(
        id = this.id,
        date = orderInputPartial.date ?: this.date,
        state = orderInputPartial.state ?: this.state,
        userID = this.userID,
    )
}

fun OrderModel.updateWith(orderInput: OrderInput): OrderModel {
    return OrderModel(
        id = this.id,
        date = orderInput.date,
        state = orderInput.state,
        userID = this.userID,
    )
}

fun OrderModel.toOrder(): Order {
    return Order(
        id = this.id,
        date = this.date,
        state = this.state,
    )
}