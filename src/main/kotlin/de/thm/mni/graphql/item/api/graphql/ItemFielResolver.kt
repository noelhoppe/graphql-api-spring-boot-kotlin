package de.thm.mni.graphql.item.api.graphql

import de.thm.mni.graphql.item.api.graphql.dto.Item
import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.order.api.graphql.dto.Order
import de.thm.mni.graphql.order.model.toOrder
import de.thm.mni.graphql.order.service.OrderService
import de.thm.mni.graphql.product.api.graphql.dto.Product
import de.thm.mni.graphql.product.model.toProduct
import de.thm.mni.graphql.product.service.ProductService
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ItemFielResolver(
    private val itemService: ItemService,
    private val orderService: OrderService,
    private val productService: ProductService
) {
    @SchemaMapping(typeName = "Item", field = "order")
    fun order(item: Item): Order {
        return this.orderService.getOrder(this.itemService.getItemByID(item.id).orderID).toOrder()
    }

    @SchemaMapping(typeName = "Item", field = "product")
    fun product(item: Item): Product {
        return this.productService.findProductByID(this.itemService.getItemByID(item.id).productID).toProduct()
    }
}