package de.thm.mni.graphql.product.api.graphql

import de.thm.mni.graphql.product.api.graphql.dto.Product
import de.thm.mni.graphql.product.api.graphql.dto.ProductInput
import de.thm.mni.graphql.product.api.graphql.dto.ProductInputPartial
import de.thm.mni.graphql.product.api.graphql.dto.toProductModel
import de.thm.mni.graphql.product.model.toProduct
import de.thm.mni.graphql.product.model.updateWith
import de.thm.mni.graphql.product.service.ProductService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class ProductController(
    private val productService: ProductService
) {
    @QueryMapping
    fun findProductByID(
        @Argument id: String
    ): Product {
        return this.productService.findProductByID(id).toProduct()
    }

    @QueryMapping
    fun findAllProducts(): List<Product> {
        return this.productService.findAllProducts().map { it.toProduct() }
    }

    @MutationMapping
    fun createProduct(
        @Argument productInput: ProductInput,
    ): Product {
        return this.productService.createProduct(productInput.toProductModel(UUID.randomUUID().toString())).toProduct()
    }

    @MutationMapping
    fun updateProduct(
        @Argument id: String,
        @Argument productInput: ProductInput
    ): Product? {
        val existingProductModel = this.productService.findProductByID(id)
        val updatedProductModel = existingProductModel.updateWith(productInput)
        return this.productService.updateProduct(updatedProductModel).toProduct()
    }

    @MutationMapping
    fun updateProductPartial(
        @Argument id: String,
        @Argument productInputPartial: ProductInputPartial
    ): Product? {
        val existingProductModel = this.productService.findProductByID(id)
        val updatedProductModel = existingProductModel.updateWith(productInputPartial)
        return this.productService.updateProduct(updatedProductModel).toProduct()
    }

    @MutationMapping
    fun deleteProduct(
        @Argument id: String
    ): Boolean {
        return this.productService.deleteProduct(id)
    }
}