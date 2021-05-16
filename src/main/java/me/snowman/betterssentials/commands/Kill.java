package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Kill implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Kill(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getUsage("kill", label)); return true;}
        User user = userManager.getUser(args[0]);
        if(user.getPlayer() == null) return true;

        sender.sendMessage(langManager.getMessage(user, "Kill"));
        user.getPlayer().setHealth(0);
        return true;
    }
}
