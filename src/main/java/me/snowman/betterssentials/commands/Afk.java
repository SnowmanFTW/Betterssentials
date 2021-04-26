package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class Afk implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    public Afk(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage(langManager.getMessage(null, "AFKUsage").replace("%command%", label)); return true;}
        User user;
        if(args.length == 0){
            Player player = (Player) sender;
            user = userManager.getUser(player);
            if(!user.isAfk()){
                user.setAfk(true);
                userManager.announceMessage(langManager.getMessage(user, "IsAFK"));
            }else{
                user.setAfk(false);
                userManager.announceMessage(langManager.getMessage(user, "IsNotAFK"));
            }
            return true;
        }
        user = userManager.getUser(args[0]);
        if(user == null){
            sender.sendMessage(langManager.getMessage(null, "PlayerNotOnline"));
            return true;
        }
        if(!user.isAfk()){
            user.setAfk(true);
            userManager.announceMessage(langManager.getMessage(user, "IsAFK"));
        }else{
            user.setAfk(false);
            userManager.announceMessage(langManager.getMessage(user, "IsNotAFK"));
        }
        return true;
    }
}
