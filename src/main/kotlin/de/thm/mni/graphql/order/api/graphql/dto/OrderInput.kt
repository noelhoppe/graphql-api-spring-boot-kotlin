package de.thm.mni.graphql.order.api.graphql.dto

import de.thm.mni.graphql.order.model.OrderModel

data class OrderInput(
    val date: String,
    val state: String,
)

fun OrderInput.toOrderModel(id: String, userID: String): OrderModel {
    return OrderModel(
        id = id,
        date = this.date,
        state = this.state,
        userID = userID,
    )
}