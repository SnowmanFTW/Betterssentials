package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Fly(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage(langManager.getMessage(null, "FlyUsage").replace("%command%", label)); return true;}
        User user;
        if(args.length == 0){
            Player player = (Player) sender;
            user = userManager.getUser(player);
            if(user.getPlayer().getAllowFlight()){
                user.getPlayer().setAllowFlight(false);
                user.sendMessage(langManager.getMessage(user, "FlyDisabled"));
            }else {
                user.getPlayer().setAllowFlight(true);
                user.sendMessage(langManager.getMessage(user, "FlyEnabled"));
            }
            return true;
        }
        user = userManager.getUser(args[0]);
        if(user.getPlayer() == null){
            sender.sendMessage(langManager.getMessage(null, "PlayerNotOnline"));
            return true;
        }
        if(user.getPlayer().getAllowFlight()){
            user.getPlayer().setAllowFlight(false);
            user.sendMessage(langManager.getMessage(user, "FlyDisabled"));
        }else {
            user.getPlayer().setAllowFlight(true);
            user.sendMessage(langManager.getMessage(user, "FlyEnabled"));
        }
        return true;
    }
}
