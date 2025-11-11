package de.thm.mni.graphql.product.model

import de.thm.mni.graphql.product.api.graphql.dto.Product
import de.thm.mni.graphql.product.api.graphql.dto.ProductInput
import de.thm.mni.graphql.product.api.graphql.dto.ProductInputPartial

data class ProductModel(
    var id: String,
    var name: String,
    var price: Float,
    var stock: Int
)

fun ProductModel.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        stock = this.stock,
    )
}

fun ProductModel.updateWith(
    productInput: ProductInput
): ProductModel {
    return ProductModel(
        id = this.id,
        name = productInput.name,
        price = productInput.price,
        stock = productInput.stock,
    )
}

fun ProductModel.updateWith(
    productInputPartial: ProductInputPartial
): ProductModel {
    return ProductModel(
        id = this.id,
        name = productInputPartial.name ?: this.name,
        price = productInputPartial.price ?: this.price,
        stock = productInputPartial.stock ?: this.stock
    )
}

val products = mutableListOf<ProductModel>()