package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class Give implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Give(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2){ sender.sendMessage(langManager.getUsage("give", label)); return true;}
        User user = userManager.userCheck(sender, args, 0);
        if(user == null) return true;

        Material material = Material.matchMaterial(args[1]);
        if(material == null){
            user.sendMessage(langManager.getMessage(user, "ItemNotValid"));
            return true;
        }
        int amount = 1;
        if(args.length >= 3) {
            if (!langManager.checkInt(args[2])) {
                sender.sendMessage(langManager.getMessage(null, "NumberNotValid"));
                return true;
            }
            amount = Integer.parseInt(args[2]);
        }
        ItemStack item = new ItemStack(material, amount);
        user.getPlayer().getInventory().addItem(item);
        user.sendMessage(langManager.getMessage(user, "GiveMe").replace("%item%", material.name().toLowerCase()).replace("%amount%", String.valueOf(amount)));
        return true;
    }
}
