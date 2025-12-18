package de.hiorcraft.nex.nexWarzone.listener

import dev.slne.surf.surfapi.bukkit.api.extensions.server
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.ban.ProfileBanList
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import java.time.Instant

class PlayerDeath() : Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        event.deathMessage(null)

        player.getWorld().playSound(
            player.getLocation(),
            Sound.ENTITY_WITHER_DEATH,
            0.2f,
            1.0f
        )

        val banList = Bukkit.getBanList(BanList.Type.PROFILE) as ProfileBanList
        banList.addBan(
            player.playerProfile,
            "Du bist gestorben!",
            null as Instant?,
            "nexWarzone"
        )

        player.kick(
            Component.text("Du bist gestorben!", NamedTextColor.RED)
        )

        val msg = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("☠", NamedTextColor.DARK_RED))
            .append(Component.text("]", NamedTextColor.GRAY))
            .append(Component.text(" ${player.name} ", NamedTextColor.DARK_GRAY))
            .append(Component.text("ist gestorben.", NamedTextColor.DARK_GRAY))

        server.sendMessage(msg)
    }
}
