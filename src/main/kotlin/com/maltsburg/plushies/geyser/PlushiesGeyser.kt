package com.maltsburg.plushies.geyser

import org.geysermc.event.subscribe.Subscribe
import org.geysermc.geyser.api.extension.Extension

typealias GeyserItemsEvent = org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
typealias GeyserHeadEvent = org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

class PlushiesGeyser : Extension {

    @Subscribe
    fun registerItems(event: GeyserItemsEvent) {
        Plushies.plushies(event)
        Plushies.books(event)
    }

    @Subscribe
    fun registerHeads(event: GeyserHeadEvent) {
        Plushies.heads(event)
    }
}