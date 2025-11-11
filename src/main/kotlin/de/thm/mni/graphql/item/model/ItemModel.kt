package de.thm.mni.graphql.item.model

import de.thm.mni.graphql.item.api.graphql.dto.Item
import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.product.api.graphql.dto.Product

data class ItemModel(
    var id: String,
    var quantity: Int,
    var orderID: String,
    var productID: String
)

val items = mutableListOf<ItemModel>()

fun ItemModel.toItem(): Item {
    return Item(
        id = this.id,
        quantity = this.quantity,
    )
}