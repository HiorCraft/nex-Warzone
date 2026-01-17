package de.hiorcraft.nex.nexWarzone.commands

import de.hiorcraft.nex.nexWarzone.util.PermissionRegistry
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument

fun EventCommand() = commandTree("Event") {
    withPermission(PermissionRegistry.COMMMAND_EVENT)
    literalArgument("start") {
        withPermission(PermissionRegistry.COMMAND_EVENT_START)
        gameStarted = true
    }
    literalArgument("stop") {
        withPermission(PermissionRegistry.COMMAND_EVENT_STOP)
        gameStarted = false
    }
    literalArgument("end") {
        withPermission(PermissionRegistry.COMMAND_EVENT_END)
        gameStarted = false
    }

}