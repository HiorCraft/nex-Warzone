package de.hiorcraft.nex.nexWarzone.listener

import de.hiorcraft.nex.nexWarzone.commands.gameStarted
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPortalEvent
import org.bukkit.event.player.PlayerTeleportEvent

class GamePortalListener : Listener {

    @EventHandler
    fun onPortal(event: PlayerPortalEvent) {
        val player = event.player

        if (!player.isOp) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onTeleport(event: PlayerTeleportEvent) {
        val player = event.player

        if (!player.isOp) {
            when (event.cause) {
                PlayerTeleportEvent.TeleportCause.NETHER_PORTAL,
                PlayerTeleportEvent.TeleportCause.END_PORTAL,
                PlayerTeleportEvent.TeleportCause.END_GATEWAY -> {
                    event.isCancelled = true
                }
                else -> {}
            }
        }
    }
}
