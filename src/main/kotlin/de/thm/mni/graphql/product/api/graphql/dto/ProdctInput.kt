package de.thm.mni.graphql.product.api.graphql.dto

import de.thm.mni.graphql.product.model.ProductModel

data class ProductInput(
    val name: String,
    val price: Float,
    val stock: Int
)

fun ProductInput.toProductModel(id: String): ProductModel {
    return ProductModel(
        id = id,
        name = this.name,
        price = this.price,
        stock = this.stock
    )
}