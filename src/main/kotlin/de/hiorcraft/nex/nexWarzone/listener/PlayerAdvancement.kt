package de.hiorcraft.nex.nexWarzone.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class PlayerAdvancementListener : Listener {

    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        val player = event.player
        val advancement = event.advancement

        Bukkit.getLogger().info("${player.name} advancement is ${advancement.key.key}")
    }
}
