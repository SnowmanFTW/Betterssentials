package me.snowman.betterssentials;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;
import me.snowman.betterssentials.utils.PluginUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Betterssentials extends JavaPlugin {
    private final UserManager userManager = new UserManager();
    private final LangManager langManager = new LangManager(this);
    private final PluginUtils pluginUtils = new PluginUtils(this, userManager, langManager);

    @Override
    public void onEnable() {
        pluginUtils.registerCommands();
        pluginUtils.registerEvents();

        langManager.setupMessages();
    }

    @Override
    public void onDisable() {

    }
}
