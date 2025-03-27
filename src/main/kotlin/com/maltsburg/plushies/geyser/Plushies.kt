package com.maltsburg.plushies.geyser

import co.akoot.plugins.plushies.Plushies.Companion.headConf
import co.akoot.plugins.plushies.util.Items
import com.maltsburg.plushies.geyser.util.Helpers.register
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

object Plushies {

    fun plushies(event: GeyserItemsEvent) {
        val plushies = Items.plushies

        for (key in plushies.sortedBy { it.second }) {
            register(event, key.first, key.second, "totem_of_undying")
            register(event, "${key.first}.st", key.second + 1, "totem_of_undying")
        }
    }

    fun books(event: GeyserItemsEvent) {
        (1..72).forEach { cmd ->
            register(event, "book.$cmd", cmd, "written_book")
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
