package de.hiorcraft.nex.nexWarzone.util

import de.hiorcraft.nex.nexWarzone.commands.startEventCommand
import de.hiorcraft.nex.nexWarzone.commands.startGameCommand
import de.hiorcraft.nex.nexWarzone.commands.whitelistAllPlayerCommand

object CommandManager {

    fun registerAll() {
        startGameCommand()
        whitelistAllPlayerCommand()
        startEventCommand()
    }
}