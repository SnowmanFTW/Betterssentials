package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    public Afk(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage("Usage"); return true;}
        User user;
        if(args.length == 0){
            Player player = (Player) sender;
            user = userManager.getUser(player);
            if(!user.isAfk()){
                user.setAfk(true);
                user.sendMessage(langManager.getMessage(user, "IsAFK"));
            }else{
                user.setAfk(false);
                user.sendMessage(langManager.getMessage(user, "IsNotAFK"));
            }
        }
        return true;
    }
}
