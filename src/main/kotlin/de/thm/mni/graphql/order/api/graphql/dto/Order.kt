package de.thm.mni.graphql.order.api.graphql.dto

data class Order(
    val id: String,
    val date: String,
    val state: String,
)