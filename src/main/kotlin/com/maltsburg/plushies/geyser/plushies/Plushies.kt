package com.maltsburg.plushies.geyser.plushies

import co.akoot.plugins.plushies.Plushies.Companion.plushieConf
import com.maltsburg.plushies.geyser.util.Helpers.buildItem
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent

object Plushies {

    fun loadPlushies(event: GeyserDefineCustomItemsEvent) {
        val config = plushieConf

        for (key in config.getKeys().sortedBy { config.getInt(it) }) {
            val cmd = config.getInt(key) ?: continue
            buildItem(key, cmd, "totem_of_undying", event)
            // statues
            buildItem("$key.st", cmd + 1, "totem_of_undying", event)
        }
    }
}
