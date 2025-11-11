package de.thm.mni.graphql.item.api.graphql.dto

import de.thm.mni.graphql.item.model.ItemModel

data class ItemInput(
    val quantity: Int,
    val productID: String
)

fun ItemInput.toItemModel(id: String, orderID: String): ItemModel {
    return ItemModel(
        id = id,
        quantity = this.quantity,
        orderID = orderID,
        productID = this.productID
    )
}