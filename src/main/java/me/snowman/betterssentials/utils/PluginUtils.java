package me.snowman.betterssentials.utils;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.Afk;
import me.snowman.betterssentials.events.AfkListener;
import me.snowman.betterssentials.player.UserManager;

import java.util.Objects;

public class PluginUtils {
    private final Betterssentials betterssentials;
    private final UserManager userManager;

    public PluginUtils(Betterssentials betterssentials, UserManager userManager){
        this.betterssentials = betterssentials;
        this.userManager = userManager;
    }
    public void registerCommands(){
        Objects.requireNonNull(betterssentials.getCommand("afk")).setExecutor(new Afk(userManager));
    }

    public void registerEvents(){
        betterssentials.getServer().getPluginManager().registerEvents(new AfkListener(userManager), betterssentials);
    }
}
