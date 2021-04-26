package me.snowman.betterssentials.utils;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.Afk;
import me.snowman.betterssentials.commands.Balance;
import me.snowman.betterssentials.commands.Ban;
import me.snowman.betterssentials.commands.Unban;
import me.snowman.betterssentials.events.AfkListener;
import me.snowman.betterssentials.events.BanListener;
import me.snowman.betterssentials.events.UserInit;
import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.CommandExecutor;

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
        registerCommand("afk", new Afk(userManager, langManager));
        registerCommand("balance", new Balance(userManager, langManager));
        registerCommand("ban", new Ban(userManager, langManager));
        registerCommand("unban", new Unban(userManager, langManager));
    }

    public void registerEvents(){
        betterssentials.getServer().getPluginManager().registerEvents(new AfkListener(userManager, langManager), betterssentials);
        betterssentials.getServer().getPluginManager().registerEvents(new UserInit(userManager), betterssentials);
        betterssentials.getServer().getPluginManager().registerEvents(new BanListener(userManager), betterssentials);
    }

    public void registerCommand(String command, CommandExecutor commandClass){
        Objects.requireNonNull(betterssentials.getCommand(command)).setExecutor(commandClass);
    }
}
