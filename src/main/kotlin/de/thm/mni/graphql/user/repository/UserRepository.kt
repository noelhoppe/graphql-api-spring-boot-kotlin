package de.thm.mni.graphql.user.repository

import de.thm.mni.graphql.order.repository.OrderRepository
import de.thm.mni.graphql.user.model.UserModel
import de.thm.mni.graphql.user.model.users

object UserRepository {
    fun findAll() = users.toList()
    fun findByID(id: String) = users.find { userModel -> userModel.id == id }
    fun deleteByID(id: String): Boolean {
        OrderRepository.findByUserID(id).forEach { orderModel ->
            OrderRepository.deleteByID(orderModel.id)
        }
        return users.removeIf { userModel -> userModel.id == id }
    }
    fun save(userModel: UserModel): UserModel {
        users.add(userModel)
        return userModel
    }

}