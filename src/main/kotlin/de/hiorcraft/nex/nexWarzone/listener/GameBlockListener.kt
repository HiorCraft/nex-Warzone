package de.hiorcraft.nex.nexWarzone.listener

import de.hiorcraft.nex.nexWarzone.commands.gameStarted
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class GameBlockListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player

        if (player.isOp) return

        if (!gameStarted) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockBreakEvent) {
        val player = event.player

        if (player.isOp) return

        if (!gameStarted) {
            event.isCancelled = true
        }
    }
}