package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Me implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Me(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(langManager.getMessage(null, "NoConsole"));
            return true;
        }
        User user = userManager.getUser(sender);
        userManager.announceMessage(langManager.getMessage(user, "Me").replace("%message%", String.join(" ", args)));
        return true;
    }
}
