package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Bukkit

fun whitelistAllPlayerCommand() = commandTree("whitelistall") {
    withPermission(PermissionRegistry.COMMAND_WHITLISTALL)

    playerExecutor { player, args ->

        val players = Bukkit.getOnlinePlayers()
        for (player in players) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist add ${player.name}")
        }
        player.sendText {
            appendPrefix()
            info("Alle Spieler wurden zur Whitelist hinzugefügt.")
        }
    }
}