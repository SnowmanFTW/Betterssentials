package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
    private final UserManager userManager;
    public Afk(UserManager userManager){
        this.userManager = userManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage("Usage"); return true;}
        User user;
        if(args.length == 0){
            user = userManager.getUser((Player) sender);
            if(!user.isAfk()){
                user.setAfk(true);
                user.sendMessage("&aAFK activated");
            }else{
                user.setAfk(false);
                user.sendMessage("&4AFK activatedn't");
            }
        }
        return true;
    }
}
