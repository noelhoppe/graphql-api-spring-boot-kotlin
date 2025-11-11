package de.thm.mni.graphql.item.api.graphql

import de.thm.mni.graphql.item.api.graphql.dto.Item
import de.thm.mni.graphql.item.api.graphql.dto.ItemInputSelf
import de.thm.mni.graphql.item.api.graphql.dto.toItemModel
import de.thm.mni.graphql.item.model.ItemModel
import de.thm.mni.graphql.item.model.toItem
import de.thm.mni.graphql.item.service.ItemService
import de.thm.mni.graphql.order.service.OrderService
import de.thm.mni.graphql.user.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class ItemController(
    private val itemService: ItemService,
) {
    @MutationMapping
    fun updateOrderItem(
        @Argument id: String,
        @Argument itemInputSelf: ItemInputSelf
    ): Item {
        val existingItem: ItemModel = this.itemService.getItemByID(id)
        return this.itemService
            .updateItem(
                itemInputSelf.toItemModel(
                    existingItem.id,
                    existingItem.id,
                    existingItem.productID
                )
            )
            .toItem()
    }
}