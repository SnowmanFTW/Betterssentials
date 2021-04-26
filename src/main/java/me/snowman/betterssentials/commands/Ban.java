package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Ban implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Ban(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getMessage(null, "BanUsage").replace("%command%", label)); return true;}
        User user = userManager.getUser(args[0]);
        if(user == null){
            sender.sendMessage(langManager.getMessage(null, "PlayerNotOnline"));
            return true;
        }
        user.setBanned(true);
        if(args.length == 1){
            user.setBanMessage(langManager.getMessage(user, "DefaultBan"));
            user.getPlayer().kickPlayer(user.getBanMessage());
            userManager.announceMessage(langManager.getMessage(user, "BanMessage"));
            return true;
        }
        String reason = String.join(" ", Arrays.stream(args).skip(1).toArray(String[]::new));
        user.setBanMessage(langManager.getMessage(null, "YouBanned").replace("%reason%", reason));
        user.getPlayer().kickPlayer(user.getBanMessage());
        userManager.announceMessage(langManager.getMessage(user, "BanMessage"));
        return true;
    }
}
