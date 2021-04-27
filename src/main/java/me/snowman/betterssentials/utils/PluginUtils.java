package me.snowman.betterssentials.utils;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.*;
import me.snowman.betterssentials.events.AfkListener;
import me.snowman.betterssentials.events.BanListener;
import me.snowman.betterssentials.events.UserInit;
import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

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
        registerCommand("broadcast", new Broadcast(userManager, langManager));
        registerCommand("clearchat", new ClearChat(userManager, langManager, betterssentials));
        registerCommand("clearinventory", new ClearInventory(userManager, langManager));
        registerCommand("economy", new Economy(userManager, langManager));
        registerCommand("unban", new Unban(userManager, langManager));
    }

    public void registerEvents(){
        registerEvent(new AfkListener(userManager, langManager));
        registerEvent(new UserInit(userManager));
        registerEvent(new BanListener(userManager));
    }

    private void registerCommand(String command, CommandExecutor commandClass){
        Objects.requireNonNull(betterssentials.getCommand(command)).setExecutor(commandClass);
    }

    private void registerEvent(Listener listenerClass){
        betterssentials.getServer().getPluginManager().registerEvents(listenerClass, betterssentials);
    }
}
