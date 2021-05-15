package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hat implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    public Hat(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(langManager.getMessage(null, "NoConsole"));
            return true;
        }
        User user = userManager.userCheck(sender, null, 0);
        if(user == null) return true;

        if(user.getPlayer().getInventory().getHelmet() == null && user.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)){
            user.sendMessage(langManager.getMessage(user, "HatInHand"));
        }else if(user.getPlayer().getInventory().getHelmet() != null && user.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)){
            user.getPlayer().getInventory().addItem(user.getPlayer().getInventory().getHelmet());
            user.getPlayer().getInventory().setHelmet(null);
            user.sendMessage(langManager.getMessage(user, "RetrievedHat"));
        }else {
            if (user.getPlayer().getInventory().getHelmet() != null) {
                user.getPlayer().getInventory().addItem(user.getPlayer().getInventory().getHelmet());
            }
            user.getPlayer().getInventory().setHelmet(user.getPlayer().getInventory().getItemInMainHand());
            user.getPlayer().getInventory().getItemInMainHand().setAmount(0);
            user.sendMessage(langManager.getMessage(user, "EnjoyHat"));
        }
        return true;
    }

}
