package de.hiorcraft.nex.nexWarzone.listener

import de.hiorcraft.nex.nexWarzone.commands.gameStarted
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class GameMoveListener : Listener {
    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player

        if (player.isOp) return

        if (!gameStarted) {
            val from = event.from
            val to = event.to
            if (from.x != to.x || from.y != to.y || from.z != to.z) {
                event.to = from.clone().apply {
                    yaw = to.yaw
                    pitch = to.pitch
                }
            }
        }
    }
}