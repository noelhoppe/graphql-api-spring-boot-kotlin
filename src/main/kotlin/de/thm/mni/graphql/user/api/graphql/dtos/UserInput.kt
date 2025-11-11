package de.thm.mni.graphql.user.api.graphql.dtos

import de.thm.mni.graphql.user.model.UserModel

data class UserInput(
    val firstName: String,
    val lastName: String,
    val email: String
)

fun UserInput.toUserModel(id: String): UserModel {
    return UserModel(
        id = id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}