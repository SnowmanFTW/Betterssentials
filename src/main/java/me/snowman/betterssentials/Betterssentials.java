package me.snowman.betterssentials;

import me.snowman.betterssentials.player.UserManager;
import me.snowman.betterssentials.utils.PluginUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Betterssentials extends JavaPlugin {
    private final UserManager userManager = new UserManager();
    private final PluginUtils pluginUtils = new PluginUtils(this, userManager);

    @Override
    public void onEnable() {
        pluginUtils.registerCommands();
        pluginUtils.registerEvents();
    }

    @Override
    public void onDisable() {

    }
}
