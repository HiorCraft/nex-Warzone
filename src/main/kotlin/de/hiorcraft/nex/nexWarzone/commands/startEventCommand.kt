package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

var EventStart = false

fun startEventCommand() = commandTree("EventStart") {

    withPermission(PermissionRegistry.COMMAND_EVENT_START)
    playerExecutor { player, args ->

        EventStart = true

        player.sendText {
            appendPrefix()
            info("Event-Server ist Offen!")
        }
    }
}