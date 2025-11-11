package de.thm.mni.graphql.product.service

import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.product.model.ProductModel
import de.thm.mni.graphql.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val itemService: ItemService
) {
    fun findAllProducts() = ProductRepository.findAll()

    fun findProductByID(id: String): ProductModel {
        return ProductRepository.findByID(id) ?: throw NoSuchElementException("Product with id $id not found")
    }

    fun createProduct(
        productModel: ProductModel
    ): ProductModel {
        val foundProduct = ProductRepository.findByID(productModel.id)
        if (foundProduct == null) {
            return ProductRepository.save(productModel)
        } else {
            throw IllegalArgumentException("Product with id ${productModel.id} already exists.")
        }
    }

    fun updateProduct(
        updatedProductModel: ProductModel
    ): ProductModel {
        val foundProductModel = this.findProductByID(updatedProductModel.id)
        foundProductModel.apply {
            name = updatedProductModel.name
            price = updatedProductModel.price
            stock = updatedProductModel.stock
        }
        return foundProductModel
    }

    fun deleteProduct(
        id: String
    ): Boolean {
        this.findProductByID(id)
        return ProductRepository.deleteByID(id)
    }
}