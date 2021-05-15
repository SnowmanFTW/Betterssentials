package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Kick implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Kick(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getUsage("kick", label)); return true;}
        User user = userManager.userCheck(sender, args, 0);
        if(user == null) return true;

        if(args.length == 1){
            if(user.getBanMessage() == null) user.setBanMessage("");
            user.setBanMessage(langManager.getMessage(user, "DefaultKick"));
        }else{
            String reason = String.join(" ", Arrays.stream(args).skip(1).toArray(String[]::new));
            user.setBanMessage(langManager.getMessage(null, "YouKicked").replace("%reason%", reason));
        }
        if(user.getPlayer() != null) user.getPlayer().kickPlayer(user.getBanMessage());
        userManager.announceMessage(langManager.getMessage(user, "KickMessage"));
        userManager.saveUser(user);
        return true;
    }
}
