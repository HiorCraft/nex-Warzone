package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

object EventStart {
    var isStarted: Boolean = false
}

fun startEventCommand() = commandTree("EventStart") {

    withPermission(PermissionRegistry.COMMAND_EVENT_START)
    playerExecutor { player, args ->


        EventStart.isStarted = true


        player.sendText {
            appendPrefix()
            info("Event-Server ist Offen!")
        }
    }
}