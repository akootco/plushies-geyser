package com.maltsburg.plushie.geyser

import com.maltsburg.plushie.geyser.plushies.Plushies.loadPlushies
import org.geysermc.event.subscribe.Subscribe
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
import org.geysermc.geyser.api.extension.Extension

class PlushieGeyser : Extension {

    @Subscribe
    fun registerItems(event: GeyserDefineCustomItemsEvent) {
        loadPlushies(event)
    }
}