package com.maltsburg.plushies.geyser

import com.maltsburg.plushies.geyser.plushies.Plushies.loadPlushies
import org.geysermc.event.subscribe.Subscribe
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
import org.geysermc.geyser.api.extension.Extension

class PlushiesGeyser : Extension {

    @Subscribe
    fun registerItems(event: GeyserDefineCustomItemsEvent) {
        loadPlushies(event)
    }
}