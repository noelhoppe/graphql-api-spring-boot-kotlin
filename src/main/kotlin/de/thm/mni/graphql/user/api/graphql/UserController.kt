package de.thm.mni.graphql.user.api.graphql

import de.thm.mni.graphql.user.api.graphql.dtos.User
import de.thm.mni.graphql.user.api.graphql.dtos.UserInput
import de.thm.mni.graphql.user.api.graphql.dtos.UserInputPartial
import de.thm.mni.graphql.user.api.graphql.dtos.toUserModel
import de.thm.mni.graphql.user.model.toUser
import de.thm.mni.graphql.user.model.updateWith
import de.thm.mni.graphql.user.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class UserController(
    private val userService: UserService,
) {
    @QueryMapping
    fun findUserByID(@Argument id: String): User {
        return this.userService.getUser(id).toUser()
    }

    @QueryMapping
    fun findAllUsers(): List<User> {
        return this.userService.getUsers().map { it.toUser() }
    }

    @MutationMapping
    fun createUser(@Argument userInput: UserInput): User {
        return this.userService.createUser(userInput.toUserModel(UUID.randomUUID().toString())).toUser()
    }

    @MutationMapping
    fun updateUser(
        @Argument id: String,
        @Argument userInput: UserInput
    ): User {
        val existingUser = this.userService.getUser(id)
        return this.userService.updateUser(existingUser.updateWith(userInput))
            .toUser()
    }

    @MutationMapping
    fun updateUserPartial(
        @Argument id: String,
        @Argument userInputPartial: UserInputPartial
    ): User? {
        val existingUser = this.userService.getUser(id)
        return this.userService
            .updateUser(existingUser.updateWith(userInputPartial))
            .toUser()
    }

    @MutationMapping
    fun deleteUser(@Argument id: String) = this.userService.deleteUser(id)
}