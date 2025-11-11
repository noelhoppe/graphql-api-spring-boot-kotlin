package de.thm.mni.graphql.user.model

import de.thm.mni.graphql.user.api.graphql.dtos.User
import de.thm.mni.graphql.user.api.graphql.dtos.UserInput
import de.thm.mni.graphql.user.api.graphql.dtos.UserInputPartial

data class UserModel(
    var id: String,
    var firstName: String,
    var lastName: String,
    var email: String,
)

val users = mutableListOf<UserModel>()

fun UserModel.toUser(): User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )
}

fun UserModel.updateWith(userInputPartial: UserInputPartial): UserModel {
    return UserModel(
        id = this.id,
        firstName = userInputPartial.firstName ?: this.firstName,
        lastName = userInputPartial.lastName ?: this.lastName,
        email = userInputPartial.email ?: this.email,
    )
}

fun UserModel.updateWith(userInput: UserInput): UserModel {
    return UserModel(
        id = this.id,
        firstName = userInput.firstName,
        lastName = userInput.lastName,
        email = userInput.email,
    )
}