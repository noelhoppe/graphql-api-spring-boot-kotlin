package de.thm.mni.graphql.user.api.graphql.dtos

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)