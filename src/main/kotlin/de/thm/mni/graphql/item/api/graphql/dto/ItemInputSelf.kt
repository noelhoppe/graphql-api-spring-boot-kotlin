package de.thm.mni.graphql.item.api.graphql.dto

import de.thm.mni.graphql.item.model.ItemModel

data class ItemInputSelf(
    val quantity: Int
)

fun ItemInputSelf.toItemModel(
    id: String,
    orderID: String,
    productID: String
): ItemModel {
    return ItemModel(
        id = id,
        quantity = this.quantity,
        orderID = orderID,
        productID = productID,
    )
}
