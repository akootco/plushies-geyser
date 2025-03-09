package com.maltsburg.plushie.geyser.plushies

import com.maltsburg.plushie.geyser.util.Helpers.buildItem
import com.maltsburg.plushie.geyser.util.Helpers.config
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent

object Plushies {

    fun loadPlushies(event: GeyserDefineCustomItemsEvent) {

        val config = config("Plushies", "plushies.conf") ?: return

        config.root().keys
            .sortedBy { config.getInt(it) }
            .forEach { key ->
                buildItem(key, config.getInt(key), "totem_of_undying", event)
                buildItem("$key.st", config.getInt(key) + 1, "totem_of_undying", event)
            }
    }
}
