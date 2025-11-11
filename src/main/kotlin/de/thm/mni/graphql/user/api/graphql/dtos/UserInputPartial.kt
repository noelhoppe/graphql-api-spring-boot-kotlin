package de.thm.mni.graphql.user.api.graphql.dtos

data class UserInputPartial(
    val firstName: String?,
    val lastName: String?,
    val email: String?
)