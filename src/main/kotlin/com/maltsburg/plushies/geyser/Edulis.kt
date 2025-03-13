package com.maltsburg.plushies.geyser

import co.akoot.plugins.edulis.Edulis.Companion.cakeConfig
import co.akoot.plugins.edulis.Edulis.Companion.headConfig
import co.akoot.plugins.edulis.Edulis.Companion.itemConfig
import com.maltsburg.plushies.geyser.util.Helpers.itemData
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

object Edulis {

    fun items(event: GeyserItemsEvent) {
        val configs = listOf(itemConfig, cakeConfig)

        for (config in configs) {
            for (key in config.getKeys().sortedBy { config.getInt(it) }) {
                val item = config.getString("$key.material")?.lowercase() ?: continue
                val cmd = config.getInt("$key.customModelData").takeIf { it != 0 } ?: continue

                event.register("minecraft:$item", itemData(key, cmd))
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