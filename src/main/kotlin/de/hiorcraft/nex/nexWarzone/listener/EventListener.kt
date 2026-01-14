package de.hiorcraft.nex.nexWarzone.listener

import de.hiorcraft.nex.nexWarzone.commands.EventStart
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class EventListener : Listener {

    @EventHandler
    fun onLogin(event: PlayerLoginEvent) {
        val player = event.player

        // OPs dürfen unabhängig vom Event-Status joinen
        if (player.isOp) return

        // Annahme: EventStart hat eine boolesche Eigenschaft `isStarted`
        if (!EventStart.isStarted) {
            val reason = Component.text()
                .append(Component.text("⚠ Event noch nicht gestartet\n", NamedTextColor.RED))
                .append(Component.text("Bitte warte, bis das Team das Event öffnet.", NamedTextColor.AQUA))
                .build()

            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, reason)
        }
    }
}


//package de.hiorcraft.nex.nexWarzone.commands
//
//object EventStart {
//    var isStarted: Boolean = false
//}