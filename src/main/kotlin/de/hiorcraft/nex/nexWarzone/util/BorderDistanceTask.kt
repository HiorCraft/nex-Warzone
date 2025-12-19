package de.hiorcraft.nex.nexWarzone.util

import de.hiorcraft.nex.nexWarzone.commands.gameStarted
import de.hiorcraft.nex.nexWarzone.plugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.scheduler.BukkitRunnable

class BorderDistanceTask() : BukkitRunnable() {

    override fun run() {
        if (!gameStarted) return

        val world = plugin.server.getWorld("world") ?: return
        val border = world.worldBorder

        val center = border.center
        val radius = border.size / 2.0

        for (player in world.players) {
            val dx = kotlin.math.abs(player.location.x - center.x)
            val dz = kotlin.math.abs(player.location.z - center.z)

            val distanceToBorder = radius - maxOf(dx, dz)

            val color = when {
                distanceToBorder < 20 -> NamedTextColor.RED
                distanceToBorder < 50 -> NamedTextColor.YELLOW
                else -> NamedTextColor.GREEN
            }

            val message = Component.text(
                "Border: ${distanceToBorder.toInt()} Blöcke entfernt",
                color
            )

            player.sendActionBar(message)
        }
    }
}