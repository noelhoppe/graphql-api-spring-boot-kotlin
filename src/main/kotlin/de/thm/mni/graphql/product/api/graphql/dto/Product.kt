package de.thm.mni.graphql.product.api.graphql.dto

data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val stock: Int,
)
