package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    public Feed(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage(langManager.getUsage("feed", label)); return true;}
        User user = userManager.userCheck(sender, args);
        if(user == null) return true;

        user.getPlayer().setFoodLevel(20);
        user.getPlayer().setSaturation(20);
        user.sendMessage(langManager.getMessage(user, "Feed"));
        return true;
    }
}
