package de.thm.mni.graphql.user.service

import de.thm.mni.graphql.order.service.OrderService
import de.thm.mni.graphql.user.model.UserModel
import de.thm.mni.graphql.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService() {
    fun getUsers() = UserRepository.findAll()

    fun getUser(id: String): UserModel {
        val foundUserModel = UserRepository.findByID(id)
        return foundUserModel ?: throw NoSuchElementException("User with ID $id not found.")
    }

    fun createUser(user: UserModel): UserModel {
        val foundUserModel = UserRepository.findByID(user.id)
        if (foundUserModel == null) {
            UserRepository.save(user)
            return user
        } else {
            throw IllegalArgumentException("User with ID ${user.id} already exists.")
        }
    }

    fun updateUser(updatedUser: UserModel): UserModel {
        val foundUserModel = this.getUser(updatedUser.id)
        foundUserModel.apply {
            firstName = updatedUser.firstName
            lastName = updatedUser.lastName
            email = updatedUser.email
        }
        return foundUserModel
    }

    fun deleteUser(userID: String): Boolean {
        val foundUserModel = this.getUser(userID)
        return UserRepository.deleteByID(foundUserModel.id)
    }
}