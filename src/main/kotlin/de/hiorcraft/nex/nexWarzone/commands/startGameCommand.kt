package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import de.hiorcraft.nex.nexWarzone.plugin
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.adventure.showTitle
import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

var gameStarted = false

fun startGameCommand() = commandTree("gamestart") {
    withPermission(PermissionRegistry.COMMNAD_GAME_START)

    playerExecutor { player, args ->

        val world = player.world
        val border = world.worldBorder
        gameStarted = true

        world.setGameRule(GameRule.SPAWN_MONSTERS, false)
        world.setGameRule(GameRule.PVP, false)
        world.setGameRule(GameRule.FALL_DAMAGE, false)

        border.center = player.location
        border.size = 5000.0
        server.sendText {
            appendPrefix()
            info("Spiel gestartet! 30 Sekunden Safe-Phase ohne PvP und Mob-Spawning.")
        }

        world.players.forEach {
            it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f)
            it.showTitle{
                title { text("LOS!")  }
            }
        }

        world.players.forEach {
            it.playSound(it.location, Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 1f)
        }


        object : BukkitRunnable() {
            override fun run() {

                world.setGameRule(GameRule.DO_MOB_SPAWNING, true)
                world.setGameRule(GameRule.PVP, true)

                server.sendText {
                    appendPrefix()
                    info("PvP und Mob-Spawning sind nun aktiviert!")
                }

                world.players.forEach {
                    it.playSound(it.location, Sound.BLOCK_BEACON_DEACTIVATE, 1f, 1f)
                }


                startBorderShrink(world)
            }
        }.runTaskLater(plugin, 30 * 20L)
    }
}

fun startBorderShrink(world: World) {

    val border = world.worldBorder

    val minSize = 20.0
    var shrink1000Count = 4
    val shrink1000Amount = 1000.0
    val shrink250Amount = 250.0

    object : BukkitRunnable() {
        override fun run() {

            val currentSize = border.size
            var newSize = currentSize
            when {
                shrink1000Count > 0 -> {
                    newSize = currentSize - shrink1000Amount
                    shrink1000Count--

                    world.players.forEach {
                        it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 1f)
                    }
                }
                currentSize > minSize -> {
                    newSize = currentSize - shrink250Amount

                    world.players.forEach {
                        it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 1f)
                    }
                }


                else -> {
                    border.setSize(newSize, 5 * 60)

                    world.players.forEach {
                        it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f)
                    }

                    Bukkit.getServer().sendText {
                        appendPrefix()
                        info("Die WorldBorder hat ihre minimale Größe von 10 Blöcken erreicht.")
                    }

                    cancel()
                    return
                }
            }

            if (newSize < minSize) newSize = minSize

            border.setSize(newSize, 5 * 60)

            server.sendText {
                appendPrefix()
                info("Die WorldBorder wurde auf ${newSize.toInt()} Blöcke verkleinert.")
            }
        }

    }.runTaskTimer(
        plugin,
        1 * 60 * 20L,
        1 * 60 * 20L
    )
}