package de.hiorcraft.nex.nexWarzone

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import de.hiorcraft.nex.nexWarzone.util.CommandManager
import de.hiorcraft.nex.nexWarzone.listener.PlayerDeath
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {

    override fun onEnable() {
        logger.info("nex-Warzone is Starting...")

        val manager = server.pluginManager

        manager.registerEvents(PlayerDeath(), this)

        CommandManager.registerAll()

        logger.info("nex-Warzone has started.")
    }

    override fun onDisable() {
        logger.info("by <3")

    }
}
