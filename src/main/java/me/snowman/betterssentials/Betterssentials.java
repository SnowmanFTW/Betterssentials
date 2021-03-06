package me.snowman.betterssentials;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;
import me.snowman.betterssentials.utils.EconomyUtils;
import me.snowman.betterssentials.utils.PluginUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Betterssentials extends JavaPlugin {
    private final LangManager langManager = new LangManager(this);
    private final me.snowman.betterssentials.files.UserManager fileUserManager = new me.snowman.betterssentials.files.UserManager(this);
    private final UserManager userManager = new UserManager(fileUserManager, langManager);
    private final PluginUtils pluginUtils = new PluginUtils(this, userManager, langManager);
    private final EconomyUtils economyUtils = new EconomyUtils(this, userManager);

    @Override
    public void onEnable() {
        pluginUtils.registerCommands();
        pluginUtils.registerEvents();

        langManager.setupMessages();
        saveDefaultConfig();
        economyUtils.hook();
    }

    @Override
    public void onDisable() {
        economyUtils.unhook();
    }
}
