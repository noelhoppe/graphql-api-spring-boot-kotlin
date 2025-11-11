package de.thm.mni.graphql.product.api.graphql

import de.thm.mni.graphql.item.api.graphql.dto.Item
import de.thm.mni.graphql.item.model.toItem
import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.product.api.graphql.dto.Product
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ProductFieldResolver(
    private val itemService: ItemService
) {
    @SchemaMapping(typeName = "Product", field = "items")
    fun items(product: Product): List<Item> {
        return this.itemService.getItemsByProductID(product.id).map { it.toItem() }
    }
}