package com.maltsburg.plushie.geyser.util

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
import org.geysermc.geyser.api.item.custom.CustomItemData
import org.geysermc.geyser.api.item.custom.CustomItemOptions
import java.io.File

object Helpers {

    private val pluginsDir = File(File(".").absoluteFile, "plugins")

    fun config(folder: String, fileName: String): Config? {
        val file = File(File(pluginsDir, folder), fileName).takeIf { it.exists() } ?: return null
        return ConfigFactory.parseFile(file)
    }

    fun buildItem(itemName: String, customModelData: Int, itemType: String, event: GeyserDefineCustomItemsEvent) {
        val data = CustomItemData.builder()
            .name(itemName.lowercase())
            .allowOffhand(true)
            .customItemOptions(CustomItemOptions.builder().customModelData(customModelData).build())
            .build()

        event.register("minecraft:$itemType", data)
    }
}

