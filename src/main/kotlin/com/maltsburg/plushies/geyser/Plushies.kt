package com.maltsburg.plushies.geyser

import co.akoot.plugins.plushies.Plushies.Companion.headConf
import co.akoot.plugins.plushies.util.Plush
import com.maltsburg.plushies.geyser.util.Helpers.itemData
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

object Plushies {

    fun plushies(event: GeyserItemsEvent) {
        val plushies = Plush.plushies

        for (key in plushies.sortedBy { it.second }) {
            event.apply {
                register("minecraft:totem_of_undying", itemData(key.first, key.second))
                register("minecraft:totem_of_undying", itemData("${key.first}.st", key.second + 1))
            }
        }
    }

    fun books(event: GeyserItemsEvent) {
        (1..72).forEach { cmd ->
            event.register("minecraft:written_book", itemData("book.$cmd", cmd))
        }
    }

    // players head do not show texture in the inventory,
    // geyser will create a resource pack for them
    fun heads(event: GeyserHeadEvent) {
        val config = headConf

        for (mob in config.getKeys()) {
            for (head in config.getKeys(mob)) { // double loop since its nested!
                val texture = config.getString("$mob.$head").takeIf { !it.isNullOrEmpty() } ?: continue
                event.register(texture, GeyserDefineCustomSkullsEvent.SkullTextureType.SKIN_HASH)
            }
        }
    }
}
