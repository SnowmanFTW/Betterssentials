package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.player.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage("Usage"); return true;}
        User user;
        if(args.length == 0){
            user =
        }
        return true;
    }
}
