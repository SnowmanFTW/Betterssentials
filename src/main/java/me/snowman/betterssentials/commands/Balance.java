package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Balance(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 && !(sender instanceof Player)){ sender.sendMessage(langManager.getUsage("balance", label)); return true;}
        User user = userManager.userCheck(sender, args, 0);
        if(user == null) return true;

        sender.sendMessage(langManager.getMessage(user, "BalanceTarget"));
        return true;
    }
}
