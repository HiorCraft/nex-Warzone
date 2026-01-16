package de.hiorcraft.nex.nexWarzone.util

import de.hiorcraft.nex.nexWarzone.commands.gameStarted
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class ActionBarTask(
    private val plugin: JavaPlugin
) : BukkitRunnable() {

    private var time = 0

    fun startGameTimers() {
        if (!gameStarted) return

        // 30 Minuten initial
        time = 30 * 60
        start()
    }

    private fun start() {
        this.runTaskTimer(plugin, 0L, 20L)
    }

    override fun run() {
        if (!gameStarted) return

        val world = plugin.server.getWorld("world") ?: return
        val border = world.worldBorder

        val center = border.center
        val radius = border.size / 2.0

        // Timer abgelaufen → 10-Minuten-Loop starten
        if (time <= 0) {
            time = 10 * 60
        }

        for (player in world.players) {

            val dx = kotlin.math.abs(player.location.x - center.x)
            val dz = kotlin.math.abs(player.location.z - center.z)
            val distanceToBorder = radius - maxOf(dx, dz)

            val color = when {
                distanceToBorder < 250 -> NamedTextColor.RED
                distanceToBorder < 450 -> NamedTextColor.YELLOW
                else -> NamedTextColor.GREEN
            }

            val minutes = time / 60
            val seconds = time % 60
            val timeFormatted = "${minutes}m ${seconds}s"

            val message = Component.text()
                .append(Component.text("Border: ", NamedTextColor.GRAY))
                .append(Component.text("${distanceToBorder.toInt()} Blöcke", color))
                .append(Component.text(" | Zeit: ", NamedTextColor.GRAY))
                .append(Component.text(timeFormatted, NamedTextColor.LIGHT_PURPLE))
                .build()

            player.sendActionBar(message)

        }

        time--
    }
}
