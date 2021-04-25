package me.snowman.betterssentials.utils;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.Afk;
import me.snowman.betterssentials.commands.Balance;
import me.snowman.betterssentials.events.AfkListener;
import me.snowman.betterssentials.events.UserInit;
import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;

import java.util.Objects;

public class PluginUtils {
    private final Betterssentials betterssentials;
    private final UserManager userManager;
    private final LangManager langManager;

    public PluginUtils(Betterssentials betterssentials, UserManager userManager, LangManager langManager){
        this.betterssentials = betterssentials;
        this.userManager = userManager;
        this.langManager = langManager;
    }
    public void registerCommands(){
        Objects.requireNonNull(betterssentials.getCommand("afk")).setExecutor(new Afk(userManager, langManager));
        Objects.requireNonNull(betterssentials.getCommand("balance")).setExecutor(new Balance(userManager, langManager));
    }

    public void registerEvents(){
        betterssentials.getServer().getPluginManager().registerEvents(new AfkListener(userManager, langManager), betterssentials);
        betterssentials.getServer().getPluginManager().registerEvents(new UserInit(userManager), betterssentials);
    }
}
