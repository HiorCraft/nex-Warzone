package de.hiorcraft.nex.nexWarzone.util

object PermissionRegistry {

    private const val PREFIX = "nexWarzone"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    const val COMMNAD_GAME_START = "$COMMAND_PREFIX.gamestart"
    const val COMMAND_WHITLISTALL = "$COMMAND_PREFIX.whitelistall"
    const val COMMAND_EVENT_START = "$COMMAND_PREFIX.eventstart"

}
