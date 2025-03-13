package com.maltsburg.plushies.geyser.util

import org.geysermc.geyser.api.item.custom.CustomItemData
import org.geysermc.geyser.api.item.custom.CustomItemOptions

object Helpers {

    fun itemData(itemName: String, customModelData: Int): CustomItemData {
        return CustomItemData.builder()
            .name(itemName.lowercase())
            .allowOffhand(true)
            .customItemOptions(CustomItemOptions.builder().customModelData(customModelData).build())
            .build()
    }
}

