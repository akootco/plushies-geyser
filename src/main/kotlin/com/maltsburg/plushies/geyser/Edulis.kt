package com.maltsburg.plushies.geyser

import co.akoot.plugins.edulis.Edulis.Companion.headConfig
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

object Edulis {
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