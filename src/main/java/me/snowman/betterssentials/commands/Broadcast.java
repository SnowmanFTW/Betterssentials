package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Broadcast implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Broadcast(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getUsage("broadcast", label)); return true;}
        String message = String.join(" ", args);
        userManager.announceMessage(langManager.getPlainMessage("Broadcast").replace("%message%", message));
        return true;
    }
}
