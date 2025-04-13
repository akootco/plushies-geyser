package com.maltsburg.plushies.geyser

import co.akoot.plugins.edulis.util.Materials.resolvedResults
import co.akoot.plugins.plushies.util.Items.customItems
import com.maltsburg.plushies.geyser.util.Helpers.register
import org.geysermc.event.subscribe.Subscribe
import org.geysermc.geyser.api.extension.Extension

typealias GeyserItemsEvent = org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent
typealias GeyserHeadEvent = org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent

class PlushiesGeyser : Extension {

    @Subscribe
    fun registerItems(event: GeyserItemsEvent) {
        Plushies.plushies(event)
        Plushies.books(event)
        items(event)
    }

    @Subscribe
    fun registerHeads(event: GeyserHeadEvent) {
        Plushies.heads(event)
        Edulis.heads(event)
    }

    private fun items(event: GeyserItemsEvent) {
        // custom model data has to be set in order or everything breaks
        // group by material here
        for ((material, keys) in customItems.plus(resolvedResults).entries.groupBy { it.value.type }) {
            // now sort by custom model data
            val sortedItems = keys.filter { it.value.itemMeta.hasCustomModelData() }
                .sortedBy { it.value.itemMeta.customModelData }

            // register that jawn
            for (key in sortedItems) {
                val cmd = key.value.itemMeta.customModelData
                register(event, key.key, cmd, material.name.lowercase())
            }
        }
    }
}