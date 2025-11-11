package de.thm.mni.graphql.product.repository

import de.thm.mni.graphql.item.repository.ItemRepository
import de.thm.mni.graphql.product.api.graphql.dto.Product
import de.thm.mni.graphql.product.model.ProductModel
import de.thm.mni.graphql.product.model.products

object ProductRepository {
    fun findAll() = products.toList()
    fun findByID(id: String) = products.find { it.id == id }
    fun save(productModel: ProductModel): ProductModel {
        products.add(productModel)
        return productModel
    }
    fun deleteByID(id: String): Boolean {
        ItemRepository.findByProductID(id).forEach { itemModel ->
            ItemRepository.deleteByID(itemModel.id)
        }
        return products.removeIf { it.id == id }
    }
}