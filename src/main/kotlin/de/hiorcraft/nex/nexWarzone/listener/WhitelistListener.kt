package de.hiorcraft.nex.nexWarzone.listener

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class WhitelistListener : Listener {

    @EventHandler
    fun onLogin(event: PlayerLoginEvent) {
        val player = event.player

        if (!Bukkit.getServer().hasWhitelist()) return

        if (!player.isWhitelisted) {
            val reason = Component.text()
                .append(Component.text("! Du bist nicht gewhitelisted !\n", NamedTextColor.RED))
                .build()

            event.disallow(
                PlayerLoginEvent.Result.KICK_WHITELIST,
                reason
            )
        }
    }
}