package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import de.hiorcraft.nex.nexWarzone.plugin
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable

const val  gameStarted = false

fun startGameCommand() = commandTree("gamestart") {
    withPermission(PermissionRegistry.COMMNAD_GAME_START)

    playerExecutor { player, args ->

        val world: World = player.world

        world.setGameRule(GameRule.DO_MOB_SPAWNING, false)
        world.setGameRule(GameRule.PVP, false)
        world.setGameRule(GameRule.FALL_DAMAGE, false)

        val border = world.worldBorder
        border.center = player.location
        border.size = 3000.0

        server.sendText {
            appendPrefix()
            info("Das ist gestartet. 30 min bis PvP und Mob-Spawning aktiviert werden.")
        }
        object : BukkitRunnable() {
            override fun run() {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, true)
                world.setGameRule(GameRule.PVP, true)
                server.sendText {
                    appendPrefix()
                    info("PvP und Mob-Spawning sind nun aktiviert!")
                }
            }
        }.runTaskLater(plugin, 30 * 60 * 20L)


        object : BukkitRunnable() {
            override fun run() {
                val newSize = border.size - 250
                if (newSize > 0) {
                    border.size = newSize
                    server.sendText {
                        appendPrefix()
                        info("Die WorldBorder wurde auf ${newSize.toInt()} Blöcke verkleinert.")
                    }
                } else {
                    cancel()
                    server.sendText {
                        appendPrefix()
                        info("Die WorldBorder hat ihre minimale Größe erreicht.")
                    }
                }
            }
        }.runTaskTimer(plugin, 30 * 60 * 20L, 20 * 60 * 20L)
    }

    plugin.server.pluginManager.registerEvents(GameMoveListener(), plugin)
}

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