package com.maltsburg.plushies.geyser

import co.akoot.plugins.edulis.Edulis.Companion.headConfig
import co.akoot.plugins.edulis.util.CreateItem.resolvedResults
import com.maltsburg.plushies.geyser.util.Helpers.itemData
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

object Edulis {

    fun items(event: GeyserItemsEvent) {
        // custom model data has to be set in order or everything breaks
        // this is such a caveman moment for minecraft fr fr, group by material here.

        // literally going to rage quit, this took like 2 hours. maybe i am the caveman
        for ((material, keys) in resolvedResults.entries.groupBy { it.value.type }) {
            // now sort by custom model data
            val sortedItems = keys.filter { it.value.itemMeta.hasCustomModelData() }
                .sortedBy { it.value.itemMeta.customModelData }

            // register that jawn
            for (key in sortedItems) {
                val cmd = key.value.itemMeta.customModelData
                event.register("minecraft:${material.name.lowercase()}", itemData(key.key, cmd))
            }
        }
    }

    fun heads(event: GeyserHeadEvent) {
        val config = headConfig

        for (item in config.getKeys()) {
            for (head in config.getKeys(item)) {
                val texture = config.getString("$item.textures").takeIf { !it.isNullOrEmpty() } ?: continue
                event.register(texture, GeyserDefineCustomSkullsEvent.SkullTextureType.SKIN_HASH)
            }
        }
    }
}