package com.maltsburg.plushies.geyser.util

import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
import org.geysermc.geyser.api.item.custom.CustomItemData
import org.geysermc.geyser.api.item.custom.CustomItemOptions

object Helpers {

    fun buildItem(itemName: String, customModelData: Int, itemType: String, event: GeyserDefineCustomItemsEvent) {

        val data = CustomItemData.builder()
            .name(itemName.lowercase())
            .allowOffhand(true)
            .customItemOptions(CustomItemOptions.builder().customModelData(customModelData).build())
            .build()

        event.register("minecraft:$itemType", data)
    }
}

