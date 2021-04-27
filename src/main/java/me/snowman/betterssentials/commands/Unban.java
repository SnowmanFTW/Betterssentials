package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Unban implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Unban(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getMessage(null, "UnbanUsage").replace("%command%", label)); return true;}
        User user = userManager.getUser(args[0]);
        if(user.isBanned()) {
            user.setBanned(false);
            userManager.saveUser(user);
            userManager.announceMessage(langManager.getMessage(user, "Unbanned"));
            return true;
        }
        sender.sendMessage(langManager.getMessage(null, "NotBanned"));
        return true;
    }
}
