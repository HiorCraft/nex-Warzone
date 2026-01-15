package de.hiorcraft.nex.nexWarzone

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import de.hiorcraft.nex.nexWarzone.listener.EventListener
import de.hiorcraft.nex.nexWarzone.listener.GameMoveListener
import de.hiorcraft.nex.nexWarzone.util.CommandManager
import de.hiorcraft.nex.nexWarzone.listener.PlayerDeath
import de.hiorcraft.nex.nexWarzone.listener.WhitelistListener
import de.hiorcraft.nex.nexWarzone.util.BorderDistanceTask
import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {

    override fun onEnable() {
        logger.info("nex-Warzone is Starting...")

        val manager = server.pluginManager

        manager.registerEvents(PlayerDeath(), this)
        manager.registerEvents(WhitelistListener(), this)
        manager.registerEvents(GameMoveListener(), this)
        manager.registerEvents(EventListener(), this)

        BorderDistanceTask().runTaskTimer(plugin, 0L, 20L)
        CommandManager.registerAll()


        val world: World? = Bukkit.getWorld("world")
        world?.setGameRule(GameRule.FALL_DAMAGE, false)
        world?.setGameRule(GameRule.SPAWN_MONSTERS, false)
        world?.setGameRule(GameRule.PVP, false)
        world?.setGameRule(GameRule.DO_WARDEN_SPAWNING, false)
        world?.setGameRule(GameRule.LOCATOR_BAR, false)

        logger.info("nex-Warzone has started.")
    }

    override fun onDisable() {
        logger.info("by <3")

    }
}
